package com.example.blogApi.Model;

import jakarta.persistence.*;

import javax.net.ssl.SSLSession;
import java.util.Collection;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;

    private Long authorId;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Reaction> reactions;

    public Post(String content) {
        this.content = content;
    }

    public Post() {

    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public Object getId() {
        return this.id;
    }

    public void addReaction(Reaction reaction) {
        this.reactions.add(reaction);
    }
}
