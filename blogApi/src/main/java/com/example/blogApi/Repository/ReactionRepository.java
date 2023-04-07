package com.example.blogApi.Repository;

import com.example.blogApi.Model.Reaction;
import com.example.blogApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findAllByUserIdAndPostId (Long userId, Long postId);
    List<Reaction> findAllByUserId (Long userId);

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    long countByPostId(Long postId);
}
