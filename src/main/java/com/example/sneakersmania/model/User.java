package com.example.sneakersmania.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 프로젝트와 연결된 DB의 넘버링 전략을 따라감
    private int id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String email;

    @ColumnDefault("'user'")
    private String role;

    @CreationTimestamp
    private Timestamp createDate;
}
