package com.example.sneakersmania.config.auth;

import com.example.sneakersmania.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(() -> {
            return "ROLE_" + user.getRole();
        });

        return collection;
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

// 1. 시큐리티가 "/auth/login" 주소 요청이 오면, 낚아채서 로그인을 진행시킴
// 2. 로그인 진행이 완료가 되면 시큐리티 session을 만들어 줌
// 3. session에 들어갈 수 있는 정보는 Authentication 객체여야 함
// 4. Authentication 안에 User 정보가 있어야 함 (이때, User 객체의 타입은 UserDetails 타입이어야 함)
// 5. 시큐리티 session -> Authentication -> UserDetails
