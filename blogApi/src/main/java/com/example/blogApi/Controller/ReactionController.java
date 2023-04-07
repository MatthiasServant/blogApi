package com.example.blogApi.Controller;

import com.example.blogApi.Model.Reaction;
import com.example.blogApi.Service.ReactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reactions")
public class ReactionController {
    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping()
    public ResponseEntity<List<Reaction>> getAllReactions() {
        return ResponseEntity.ok(reactionService.getAllReactions());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reaction>> getReactionsByUserId(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(reactionService.getReactionsByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reaction> getReactionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reactionService.getReactionById(id));
    }

    @PutMapping("/{userId}/{postId}")
    public ResponseEntity<Reaction> updateReactionByUserId(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId, @RequestBody Reaction reaction) {
        return ResponseEntity.ok(reactionService.updateReactionByUserIdAndPostId(reaction, userId, postId));
    }

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Reaction> createReactionForPostWithUserId(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId,  @RequestBody Reaction reaction) {
        return ResponseEntity.ok(reactionService.createReactionForPostWithUserId(reaction, postId, userId));
    }

    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<Void> deleteReactionByUserIdAndPostId(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        reactionService.deleteReactionByUserAndPostId(postId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Reaction>> getReactionsByPostId(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(reactionService.getReactionsByPostId(id));
    }
}
