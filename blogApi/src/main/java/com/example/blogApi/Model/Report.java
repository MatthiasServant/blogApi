package com.example.blogApi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(ReactionId.class)
public class Report {
    @Id
    private Long userId;
    @Id
    private Long postId;
    private String reason;

    public Report(Long userId, Long postId, String reason) {
        this.userId = userId;
        this.postId = postId;
        this.reason = reason;
    }

    public Report() {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
