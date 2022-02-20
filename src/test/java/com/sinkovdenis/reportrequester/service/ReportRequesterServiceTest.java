package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.mapper.ReportRequestMapper;
import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import com.sinkovdenis.reportrequester.persistence.repo.AcceptedRequestRepository;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequesterServiceTest {
    
    private static final long TEST_REQUEST_ID = 100L;
    
    @InjectMocks
    private ReportRequesterService service;
    
    @Mock
    private ReportRequestMapper mapper;
    @Mock
    private AcceptedRequestRepository repository;
    @Mock
    private ReportRequestPublisher publisher;

    @Mock
    private ByDateReportRequest byDateReportRequest;
    @Mock
    private ByIdsReportRequest byIdsReportRequest;
    @Mock
    private AcceptedRequestEntity acceptedRequestEntity;
    

    @Test
    public void testRequest_byDate() {
        when(mapper.toAcceptedRequestEntity(byDateReportRequest)).thenReturn(acceptedRequestEntity);
        when(repository.save(acceptedRequestEntity)).thenReturn(acceptedRequestEntity);
        when(acceptedRequestEntity.getRequestId()).thenReturn(TEST_REQUEST_ID);
        
        service.requestReport(byDateReportRequest);
        
        verify(mapper).toAcceptedRequestEntity(byDateReportRequest);
        verify(repository).save(acceptedRequestEntity);
        verify(byDateReportRequest).setRequestId(TEST_REQUEST_ID);
        verify(publisher).publish(byDateReportRequest);
    }

    @Test
    public void testRequest_byIds() {
        when(mapper.toAcceptedRequestEntity(byIdsReportRequest)).thenReturn(acceptedRequestEntity);
        when(repository.save(acceptedRequestEntity)).thenReturn(acceptedRequestEntity);
        when(acceptedRequestEntity.getRequestId()).thenReturn(TEST_REQUEST_ID);
        
        service.requestReport(byIdsReportRequest);

        verify(mapper).toAcceptedRequestEntity(byIdsReportRequest);
        verify(repository).save(acceptedRequestEntity);
        verify(byIdsReportRequest).setRequestId(TEST_REQUEST_ID);
        verify(publisher).publish(byIdsReportRequest);
    }

    @Test
    public void testShowReportTypes() {
        assertThat(service.showReportTypes()).isNotBlank();
    }
}
