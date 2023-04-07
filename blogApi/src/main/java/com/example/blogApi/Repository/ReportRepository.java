package com.example.blogApi.Repository;

import com.example.blogApi.Model.ReactionId;
import com.example.blogApi.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, ReactionId> {
    List<Report> findAllByUserId(Long userId);

    List<Report> findAllByPostId(Long id);

    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
