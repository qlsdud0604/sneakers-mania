package com.example.sneakersmania.service;

import com.example.sneakersmania.model.RoleType;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void join(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        user.setRole(RoleType.USER);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });

        return user;
    }

    @Transactional
    public void updateUser(User user) {
        User persistance = userRepository.findById(user.getId())   // 영속화
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
                });

        if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encodedPassword = encoder.encode(rawPassword);

            persistance.setEmail(user.getEmail());
            persistance.setPassword(encodedPassword);
        }
    }

}
