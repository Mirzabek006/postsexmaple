package com.example.demo.service;

import com.example.demo.dto.PostCreate;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.error.BaseResponse;
import com.example.demo.error.DataNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

public interface PostService {
    List<Post> findAllUsers();



    PostResponse create(@Valid PostCreate request);


    BaseResponse<PostResponse> update(Long id, PostCreate creator) throws DataNotFoundException;
}
