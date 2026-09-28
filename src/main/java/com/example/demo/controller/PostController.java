package com.example.demo.controller;

import com.example.demo.dto.PostCreate;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.entity.Post;
import com.example.demo.error.BaseResponse;
import com.example.demo.error.DataNotFoundException;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Post>> findAllUsers() {
        List<Post> post = postService.findAllUsers();
        return ResponseEntity.ok(post);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<PostResponse>> create(@Valid @RequestBody PostCreate request) throws DataNotFoundException {
        PostResponse result = postService.create(request); // service nomingizga moslang

        BaseResponse<PostResponse> response = new BaseResponse<>(
                true,
                "Post successfully created",
                result,
                201,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


//@PutMapping("/update/{id}")
//    public ResponseEntity<BaseResponse<PostResponse>> update (@PathVariable Long id, @RequestBody PostCreate creator)throws DataNotFoundException {
//        BaseResponse<PostResponse>response1 = postService.update(id,creator);
//        return ResponseEntity.ok(response1);
//
//}

}
