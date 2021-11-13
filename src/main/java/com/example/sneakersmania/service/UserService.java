package com.example.sneakersmania.service;

import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(User user) {
        userRepository.save(user);
    }


}
