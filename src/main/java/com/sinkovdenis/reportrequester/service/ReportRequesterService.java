package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.mapper.ReportRequestMapper;
import com.sinkovdenis.reportrequester.model.ReportType;
import com.sinkovdenis.reportrequester.model.request.GenericReportRequest;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import com.sinkovdenis.reportrequester.persistence.repo.AcceptedRequestRepository;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportRequesterService {

    private final ReportRequestMapper mapper;
    private final AcceptedRequestRepository repository;
    private final ReportRequestPublisher publisher;
    
    @Transactional
    public <R extends GenericReportRequest> void requestReport(R request) {
        AcceptedRequestEntity requestEntity = mapper.toAcceptedRequestEntity(request);
        Long acceptedId = repository.save(requestEntity).getRequestId();
        request.setRequestId(acceptedId);
        publisher.publish(request);
    }

    public String showReportTypes() {
        return Arrays.stream(ReportType.values()).map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

}
