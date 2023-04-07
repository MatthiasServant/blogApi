package com.example.blogApi.Service;

import com.example.blogApi.Model.Reaction;
import com.example.blogApi.Model.Report;
import com.example.blogApi.Repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void reportPost(Long userId, Long postId, String reason) {
        reportRepository.save(new Report(userId, postId, reason));
    }

    public void deleteReportByUserAndPostId(Long postId, Long userId) {
        reportRepository.findAllByUserId(userId).stream()
                .filter(r -> r.getPostId().equals(postId))
                .findFirst()
                .ifPresent(reportRepository::delete);
    }

    public Report updateReportByUserIdAndPostId(Report report, Long userId, Long postId){
        reportRepository.findAllByUserId(userId).stream()
                .filter(r -> r.getPostId().equals(postId))
                .findFirst()
                .ifPresent(r -> {
                    r.setReason(report.getReason());
                    reportRepository.save(r);
                });
        return reportRepository.findAllByUserId(userId).stream()
                .filter(r -> r.getPostId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    public List<Report> getReportsByUserId(Long id) {
        return reportRepository.findAllByUserId(id);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getReportByPostId(Long id) {
        return reportRepository.findAllByPostId(id);
    }
}
