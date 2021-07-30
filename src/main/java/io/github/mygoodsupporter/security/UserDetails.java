package io.github.mygoodsupporter.security;


import io.github.mygoodsupporter.domain.user.Authority;
import io.github.mygoodsupporter.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
@Getter @Setter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private Long id;
    private String username;
    private String password;
    private int enabled;
    private List<Authority> authorities = new ArrayList<>();

    private String email;

    public UserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();

        this.authorities = user.getAuthorities();
        this.enabled = user.getEnabled();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        for (Authority authority: authorities) {
            authList.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return authList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
