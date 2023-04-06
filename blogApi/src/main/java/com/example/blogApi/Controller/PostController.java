package com.example.blogApi.Controller;

import com.example.blogApi.Model.Post;
import com.example.blogApi.Model.User;
import com.example.blogApi.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Post> createPostForUser(@PathVariable("id") Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.createPostForUser(post, id));
    }

    @PutMapping("/{postId}/{userId}")
    public ResponseEntity<Post> updatePostByUserId(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePostByUserId(postId, userId, post));
    }

    @DeleteMapping("/{postId}/{userId}")
    public ResponseEntity<Void> deletePostByUserId(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        postService.deletePostByUserId(postId, userId);
        return ResponseEntity.ok().build();
    }
}
