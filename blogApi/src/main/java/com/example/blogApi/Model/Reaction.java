package com.example.blogApi.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(ReactionId.class)
public class Reaction {
    private ReactionType type;

    @Id
    private Long userId;

    @Id
    private Long postId;

    public Reaction(ReactionType reactionType, Long userId, Long postId) {
        this.type = reactionType;
        this.userId = userId;
        this.postId = postId;
    }

    public Reaction() {

    }


    public ReactionType getType() {
        return this.type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setReactionType(ReactionType reactionType) {
        this.type = reactionType;
    }
}

