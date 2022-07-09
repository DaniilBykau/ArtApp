package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
