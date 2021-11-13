package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequesterService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sinkovdenis.reportrequester.exception.ErrorCodes.ILLEGAL_REQUEST;
import static com.sinkovdenis.reportrequester.exception.ErrorMessages.ILLEGAL_REQUEST_MESSAGE;

@RestController
@RequiredArgsConstructor
public class ReportRequestController {

    private final ReportRequesterService service;

    @GetMapping("/types")
    public String reportTypes() {
        return service.showReportTypes();
    }

    @PostMapping("/request/by-date")
    @ApiResponses(
            value = {
                    @ApiResponse(code = ILLEGAL_REQUEST, message = ILLEGAL_REQUEST_MESSAGE),
            }
    )
    public String request(@RequestBody ByDateReportRequest request) {
        service.requestReport(request);
        return request.getEmail();
    }

    @PostMapping("/request/by-ids")
    @ApiResponses(
            value = {
                    @ApiResponse(code = ILLEGAL_REQUEST, message = ILLEGAL_REQUEST_MESSAGE),
            }
    )
    public ResponseEntity<String> request(@RequestBody ByIdsReportRequest request) {
        service.requestReport(request);
        return ResponseEntity.ok().body(request.getEmail());
    }
}
