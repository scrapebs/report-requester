package com.sinkovdenis.reportrequester.mapper;

import com.sinkovdenis.reportrequester.model.request.AcceptedRequest;
import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static com.sinkovdenis.reportrequester.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ReportRequestMapperTest {
    
    @Spy
    @InjectMocks
    private ReportRequestMapper mapper;

    @Test
    void toAcceptedRequestEntity() {
        ByDateReportRequest request = createByDateReportRequest();

        AcceptedRequestEntity entity = new AcceptedRequestEntity();
        entity.setReportType(request.getReportType());
        entity.setCreatedBy(request.getEmail());
        entity.setCreationDate(DATE_TO);
        entity.setRequestSource(mapper.toJson(request));
        doReturn(DATE_TO).when(mapper).getCurrentDate();
        
        assertThat(mapper.toAcceptedRequestEntity(request))
                .isEqualTo(entity);
    }

    @Test
    void toAcceptedRequest() {
        final AcceptedRequestEntity entity = createAcceptedRequestEntity();
        
        assertThat(mapper.toAcceptedRequest(entity))
                .isEqualTo(AcceptedRequest.builder()
                        .requestId(entity.getRequestId())
                        .email(entity.getCreatedBy())
                        .reportType(entity.getReportType())
                        .requestSource(entity.getRequestSource())
                        .creationDate(entity.getCreationDate())
                        .build());
    }

    @Test
    void getCurrentDate() {
        assertThat(mapper.getCurrentDate())
                .isNotNull()
                .isInstanceOf(Date.class);
    }
}