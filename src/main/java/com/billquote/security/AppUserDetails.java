package com.billquote.security;

import com.billquote.entity.Utilisateur; // adapte le package/nom de ta classe utilisateur
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private final Utilisateur user;

    public AppUserDetails(Utilisateur user) {
        this.user = user;
    }

    /** ✅ C’est ça qu’on utilisera dans FactureServiceImpl */
    public Long getIdUtilisateur() {
        return user.getId(); // adapte au nom exact du champ id dans ton entity
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // ou getLogin(), etc.
    }

    @Override
    public String getPassword() {
        return user.getMotDePasse(); // adapte au nom du champ mot de passe
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // si ton Utilisateur a un champ "role" (String)
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
