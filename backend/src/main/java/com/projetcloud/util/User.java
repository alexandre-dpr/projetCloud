package com.projetcloud.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class User implements UserDetails {

    @Schema(description = "Nom", example = "Joueur1")
    private String username;
    @Schema(description = "Mot de passe", example = "password")
    private String password;
    @Schema(description = "Roles")
    private List<Roles> roles;

    public User(String username, String password, List<Roles> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public enum Roles {
        ROLE_USER
    }

    public User(){
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
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
