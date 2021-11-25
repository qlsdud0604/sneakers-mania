package com.example.sneakersmania.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 프로젝트와 연결된 DB의 넘버링 전략을 따라감
    private int id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)   // DB는 RoleType이라는 게 없음. 따라서, 해당 Enum이 String이라는 것을 명시
    private RoleType role;

    private String oauth;

    @CreationTimestamp
    private Timestamp createDate;
}
