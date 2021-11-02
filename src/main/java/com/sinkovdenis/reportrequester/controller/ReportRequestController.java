package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.GenericReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportRequestController {

    private final ReportRequestService service;

    @PostMapping("/request")
    public ResponseEntity<String> request(@RequestBody GenericReportRequest request) {
        service.requestReport(request);
        return ResponseEntity.ok().body(request.getEmail());
    }
}
