package com.example.sneakersmania.config.auth;

import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override   //
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다.");
                });

        return new PrincipalDetails(principal);
    }
}

// 1. "/auth/login" 요청이 오면 자동으로 UserDetailsService 타입으로 IoC에 등록된 loadUserByUser 메서드가 실행됨
// 2. loadUserByUsername() 메서드에 의해서 리턴된 UserDetails 객체는 Authentication 객체에 들어가게 됨
