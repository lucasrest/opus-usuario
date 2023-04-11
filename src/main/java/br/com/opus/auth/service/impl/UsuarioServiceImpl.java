package br.com.opus.auth.service.impl;

import br.com.opus.auth.config.CustomPasswordEncoder;
import br.com.opus.auth.enums.Status;
import br.com.opus.auth.exception.EntidadeNaoEncontradaException;
import br.com.opus.auth.exception.NegocioException;
import br.com.opus.auth.model.RecuperarConta;
import br.com.opus.auth.model.Usuario;
import br.com.opus.auth.model.constants.EXCEPTION;
import br.com.opus.auth.model.dto.LoginRequestDTO;
import br.com.opus.auth.model.dto.RecuperaSenhaDTO;
import br.com.opus.auth.model.dto.UsuarioDTO;
import br.com.opus.auth.repository.PerfilRepository;
import br.com.opus.auth.repository.RecuperarContaRepository;
import br.com.opus.auth.repository.UsuarioRepository;
import br.com.opus.auth.security.CryptoUtil;
import br.com.opus.auth.service.UsuarioService;
import br.com.opus.auth.util.RandomUtils;
import br.com.opus.auth.exception.EntidadeNaoEncontradaException;
import br.com.opus.auth.exception.NegocioException;
import br.com.opus.auth.repository.PerfilRepository;
import br.com.opus.auth.security.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static br.com.opus.auth.model.constants.EXCEPTION.USUARIO_NAO_ENCONTRADO;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioDTO> implements UsuarioService {

    @Value("${url.login}")
    private String urlLogin;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private CryptoUtil cryptoUtil;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private RecuperarContaRepository recuperarContaRepository;

    @Override
    public Page<Usuario> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (!usuario.isPresent())
            throw new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO);
        return usuario.get();
    }

    @Override
    public Usuario incluir(Usuario usuario) {
        repository.findByEmailLikeIgnoreCase(usuario.getEmail()).
                ifPresent(s -> {
                    throw new NegocioException(EXCEPTION.USUARIO_JA_EXISTE);
                });

        usuario.getPerfisUsuario().forEach(profile -> perfilRepository.findFirstByDescricaoLikeIgnoreCase(profile).
                orElseThrow(() ->
                        new EntidadeNaoEncontradaException(EXCEPTION.PERFIL_NAO_ENCONTRADO))
        );
        usuario.setSenha(customPasswordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        Usuario usuarioBanco = findByUsername(usuario.getEmail());
        String passDescript = cryptoUtil.decrypt(usuario.getSenha());
        usuarioBanco.setSenha(customPasswordEncoder.encode(passDescript));
        return repository.save(usuarioBanco);
    }

    @Override
    public void excluir(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO));
        usuario.setStatus(Status.DESATIVADO.getValue());
        repository.save(usuario);
    }


    @Override
    public Usuario findByUsername(String username) {
        return repository.findByEmailLikeIgnoreCase(username)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO)
                );
    }

    @Override
    public Usuario enviarEmailCodigoRecuperacao(String email) {
        String codigo = RandomUtils.gerarCodigoRecuperacao();
        Usuario usuario = repository.findByEmailLikeIgnoreCase(email).orElseThrow(() -> new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO));
        RecuperarConta recuperarConta = RecuperarConta.builder().codigoRecuperacao(codigo).usuario(usuario).expiracao(LocalDateTime.now().plusHours(2)).build();
        recuperarContaRepository.save(recuperarConta);
        //TODO
        //Enviar email com o codigo de recuperação
        return usuario;
    }

    @Override
    public Usuario resetarSenha(String email, RecuperaSenhaDTO dto) {
        Usuario usuario = repository.findByEmailLikeIgnoreCase(email).orElseThrow(() ->
                new EntidadeNaoEncontradaException(USUARIO_NAO_ENCONTRADO));
        RecuperarConta recuperarConta = recuperarContaRepository.
                findByCodigoRecuperacaoAndUsuario_IdEquals(dto.getCodigoRecuperacao(), usuario.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Codigo de recuperação não encontrado"));
        recuperarContaRepository.delete(recuperarConta);
        usuario.setSenha(dto.getSenha());
        atualizar(usuario);
        return usuario;
    }

    @Override
    public Object login(String authorization, LoginRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", authorization);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", request.getUsername());
        map.add("password", cryptoUtil.encrypt(request.getPassword()));
        map.add("grant_type", request.getGrantType());

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Map<String, Object>> entity = new HttpEntity(map, headers);
        try {
            return restTemplate.postForEntity(urlLogin, entity, String.class).getBody();
        } catch (Exception e) {
            throw new UnauthorizedClientException(EXCEPTION.FALHA_NA_AUTENTICACAO);
        }
    }
}
