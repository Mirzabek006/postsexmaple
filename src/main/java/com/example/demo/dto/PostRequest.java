package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String body;
    private Integer title;
    @Column(name = "user_name")
    private String user;

}
