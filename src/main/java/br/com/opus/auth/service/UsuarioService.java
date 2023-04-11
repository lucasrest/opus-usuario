package br.com.opus.auth.service;

import br.com.opus.auth.model.Usuario;
import br.com.opus.auth.model.dto.LoginRequestDTO;
import br.com.opus.auth.model.dto.RecuperaSenhaDTO;
import br.com.opus.auth.model.dto.UsuarioDTO;

public interface UsuarioService extends BaseCRUDService<Usuario, UsuarioDTO> {

    Usuario findByUsername(String username);

    Usuario enviarEmailCodigoRecuperacao(String email);

    Usuario resetarSenha(String email, RecuperaSenhaDTO dto);

    Object login(String authorization, LoginRequestDTO login);
}