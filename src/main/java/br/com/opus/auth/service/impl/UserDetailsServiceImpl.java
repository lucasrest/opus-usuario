package br.com.opus.auth.service.impl;

import br.com.opus.auth.model.CustomUserDetails;
import br.com.opus.auth.model.Usuario;
import br.com.opus.auth.model.constants.EXCEPTION;
import br.com.opus.auth.repository.UsuarioRepository;
import br.com.opus.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailLikeIgnoreCase(userName)
                .orElseThrow(() -> new UnauthorizedClientException(EXCEPTION.FALHA_NA_AUTENTICACAO));

        return new CustomUserDetails(usuario);
    }
}