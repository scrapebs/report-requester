package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.model.ReportType;
import com.sinkovdenis.reportrequester.model.request.GenericReportRequest;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportRequesterService {

    private final ReportRequestPublisher publisher;

    public String showReportTypes() {
        return Arrays.stream(ReportType.values()).map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public void requestReport(GenericReportRequest request) {
        publisher.publish(request);
    }
}
