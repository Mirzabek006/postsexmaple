package com.example.demo.service.impl;

import com.example.demo.dto.PostCreate;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.entity.Post;
import com.example.demo.error.BaseResponse;
import com.example.demo.error.DataNotFoundException;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllUsers() {
        return postRepository.findAll() ;
    }

    @Override
    public PostResponse create(PostCreate request) {

    Post post=Post.builder()
            .body(request.getBody())
            .title(request.getTitle())
            .user(request.getUser())
            .build();
    Post save=postRepository.save(post);
        PostResponse response = new PostResponse(
                save.getId(),
                save.getBody(),
                save.getTitle(),
                save.getUser()
        );
     return new BaseResponse<>(
                true,
                "Post successfully updated",
                response,
                200,
                LocalDateTime.now()
        ).getData();
    }

    @Override
    public BaseResponse<PostResponse> update(Long id, PostCreate creator) throws DataNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Post with id " + id + " not found"
                        ));

        post.setBody(creator.getBody());
        post.setTitle(creator.getTitle());
        post.setUser(creator.getUser());

        Post save = postRepository.save(post);

        PostResponse response = new PostResponse(
                save.getId(),
                save.getBody(),
                save.getTitle(),
                save.getUser()
        );

        return new BaseResponse<>(
                true,
                "Post successfully updated",
                response,
                200,
                LocalDateTime.now()
        );

    }


}
