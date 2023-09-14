package com.generation.lojaDeGames.security;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import com.generation.lojaDeGames.model.Usuario;

public class UserDetailsImpl implements UserDetails {


    //mostrar a versao da classe
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

    //armazena todas as roles que o usuario tem acesso
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(Usuario user) {
        this.userName = user.getUsuario();
        this.password = user.getSenha();
    }

    public UserDetailsImpl() {	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}