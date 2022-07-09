package com.example.demo.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(name="image")
    private byte[] image;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long postId;
}
