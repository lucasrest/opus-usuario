package br.com.opus.auth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {
    private User user;
    private String name;
    private List<String> profiles;
    private List<String> modulos;

    public CustomUserDetails(Usuario usuario) {
        this.user = new User(usuario.getEmail(), usuario.getSenha(), getRoles(usuario));
        this.name = usuario.getNome();
        this.profiles = usuario.getPerfisUsuario();
        this.modulos = usuario.getModulos();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public String getName() {
        return name;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    private Collection<? extends GrantedAuthority> getRoles(Usuario usuario) {
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        usuario.getPerfis().forEach(perfilUsuario ->
                perfilUsuario.getPerfil().getModulos().forEach(moduloPerfil ->
                        moduloPerfil.getModulo().getOperacoes().forEach(perfilOperacao ->
                                roles.add(new SimpleGrantedAuthority(perfilOperacao.getOperacao().getCodigo())
                                ))));
        return roles;
    }

    public List<String> getModulos() {
        return modulos;
    }
}
