package com.example.blogApi.Service;

import com.example.blogApi.Model.Post;
import com.example.blogApi.Model.Reaction;
import com.example.blogApi.Model.User;
import com.example.blogApi.Repository.PostRepository;
import com.example.blogApi.Repository.ReactionRepository;
import com.example.blogApi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {
    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public ReactionService(ReactionRepository reactionRepository, UserRepository userRepository, PostRepository postRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public Reaction createReactionForPostWithUserId(Reaction reaction, Long postId, Long userId) {
        return reactionRepository.save(new Reaction( reaction.getType(), userId, postId));
    }

    public Reaction updateReactionByUserIdAndPostId(Reaction reaction, Long userId, Long postId){
        reactionRepository.findAllByUserId(userId).stream()
                .filter(r -> r.getPostId().equals(postId))
                .findFirst()
                .ifPresent(r -> {
                    r.setType(reaction.getType());
                    reactionRepository.save(r);
                });
        return reactionRepository.findAllByUserId(userId).stream()
                .filter(r -> r.getPostId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    public void deleteReactionByUserAndPostId(Long postId, Long userId) {
        reactionRepository.findAllByUserId(userId).stream()
                .filter(r -> r.getPostId().equals(postId))
                .findFirst()
                .ifPresent(reactionRepository::delete);
    }

    public List<Reaction> getReactionsByUserId(Long id) {
        return reactionRepository.findAllByUserId(id);
    }

    public Reaction getReactionById(Long id) {
        return reactionRepository.findById(id).orElse(null);
    }

    public List<Reaction> getReactionsByPostId(Long id) {
        return reactionRepository.findAllByPostId(id);
    }
}
