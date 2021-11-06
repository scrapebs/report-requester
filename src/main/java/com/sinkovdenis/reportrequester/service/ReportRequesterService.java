package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.model.GenericReportRequest;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportRequesterService {

    private final ReportRequestPublisher publisher;

    public void requestReport(GenericReportRequest request) {
        publisher.publish(request);
    }
}
