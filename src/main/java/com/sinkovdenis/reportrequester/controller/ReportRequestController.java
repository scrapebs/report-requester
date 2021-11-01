package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.ReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReportRequestController {

    private final ReportRequestService service;

    @PostMapping("/request")
    public ResponseEntity<String> request(@RequestBody ReportRequest request) {
        service.request(request);
        return ResponseEntity.ok().body(request.getEmail());
    }
}
