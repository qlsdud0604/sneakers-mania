package com.example.sneakersmania.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob   // 대용량 데이터 저장시 사용
    private String content;

    private int count;

    @ManyToOne   // N(게시물) : 1(사용자)으로 연관 관계 설정 -> ManyToOne의 기본전략은  EAGER
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)   // mappedBy : 연관관계의 주인이 아니라는 뜻 (해당 속성은 FK가 아님)
    @JsonIgnoreProperties({"board"})   // 무한 참조 방지
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}
