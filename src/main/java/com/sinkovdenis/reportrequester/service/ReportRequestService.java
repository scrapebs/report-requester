package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.model.ReportRequest;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportRequestService {

    private final ReportRequestPublisher publisher;

    public void request(ReportRequest request) {
        publisher.publish(request);
    }
}
