package com.sinkovdenis.reportrequester.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinkovdenis.reportrequester.model.request.AcceptedRequest;
import com.sinkovdenis.reportrequester.model.request.GenericReportRequest;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportRequestMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <R extends GenericReportRequest> AcceptedRequestEntity toAcceptedRequestEntity(R request) {
        AcceptedRequestEntity entity = new AcceptedRequestEntity();
        entity.setReportType(request.getReportType());
        entity.setCreatedBy(request.getEmail());
        entity.setCreationDate(getCurrentDate());
        entity.setRequestSource(toJson(request));
        return entity;
    }
    
    public AcceptedRequest toAcceptedRequest(AcceptedRequestEntity entity) {
        AcceptedRequest acceptedRequest = new AcceptedRequest();
        acceptedRequest.setReportType(entity.getReportType());
        acceptedRequest.setRequestId(entity.getRequestId());
        acceptedRequest.setRequestSource(entity.getRequestSource());
        acceptedRequest.setCreationDate(entity.getCreationDate());
        acceptedRequest.setEmail(entity.getCreatedBy());
        return acceptedRequest;
    }
    
    String toJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    Date getCurrentDate() {
        return new Date();
    }
}
