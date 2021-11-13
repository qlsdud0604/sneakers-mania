package com.example.sneakersmania.repository;

import com.example.sneakersmania.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
