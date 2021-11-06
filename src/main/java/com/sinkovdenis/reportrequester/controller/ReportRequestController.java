package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportRequestController {

    private final ReportRequesterService service;

    @PostMapping("/request/by-date")
    public String request(@RequestBody ByDateReportRequest request) {
        service.requestReport(request);
        return request.getEmail();
    }

    @PostMapping("/request/by-ids")
    public ResponseEntity<String> request(@RequestBody ByIdsReportRequest request) {
        service.requestReport(request);
        return ResponseEntity.ok().body(request.getEmail());
    }
}
