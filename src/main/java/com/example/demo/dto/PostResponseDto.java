package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto{
     private Long id;
     private String body;
     private Integer title;
     private String user;

}
