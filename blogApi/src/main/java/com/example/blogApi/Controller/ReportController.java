package com.example.blogApi.Controller;

import com.example.blogApi.Model.Report;
import com.example.blogApi.Service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(reportService.getReportsByUserId(id));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Report>> getReportsByPostId(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(reportService.getReportByPostId(id));
    }

    @PutMapping("/{userId}/{postId}")
    public ResponseEntity<Report> updateReportByUserId(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId, @RequestBody Report report) {
        return ResponseEntity.ok(reportService.updateReportByUserIdAndPostId(report, userId, postId));
    }

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Report> createReportForPostWithUserId(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId,  @RequestBody Report report) {
        reportService.reportPost(userId, postId, report.getReason());
        return ResponseEntity.ok().build();
    }
}
