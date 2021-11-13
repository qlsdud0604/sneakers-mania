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


}
