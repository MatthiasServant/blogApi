package com.example.blogApi.Service;

import com.example.blogApi.Model.Post;
import com.example.blogApi.Repository.PostRepository;
import com.example.blogApi.Repository.ReactionRepository;
import com.example.blogApi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ReactionRepository reactionRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, ReactionRepository reactionRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
    }

    public List<Post> getPostsByUserId(Long id) {
        return postRepository.findAllByAuthorId(id);
    }

    public Post createPostForUser(Post post, Long id) {
        userRepository.findById(id).ifPresent(user -> {
            post.setAuthorId((Long) user.getId());
        });
        return postRepository.save(post);
    }

    public Post updatePostByUserId(Long postId, Long userId, Post post) {
        postRepository.findById(postId).ifPresent(p -> {
            if (p.getAuthorId().equals(userId)) {
                p.setContent(post.getContent());
                postRepository.save(p);
            }
        });
        return postRepository.findById(postId).orElse(null);
    }

    public void deletePostByUserId(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null && post.getAuthorId().equals(userId)) {
            reactionRepository.deleteAll(reactionRepository.findAllByPostId(postId));
            postRepository.deleteById(postId);
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
