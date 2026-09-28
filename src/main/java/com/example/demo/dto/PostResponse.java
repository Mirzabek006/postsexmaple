package com.example.demo.dto;

public record PostResponse(
        Long id,
        String body,
        Integer title,
        String user
) {

}
