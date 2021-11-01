package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.model.ReportRequest;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestServiceTest {

    @Mock
    private ReportRequestPublisher publisher;

    @Mock
    private ReportRequest reportRequest;

    @InjectMocks
    private ReportRequestService service;

    @Test
    public void testRequest() {
        service.request(reportRequest);
        verify(publisher).publish(reportRequest);
    }
}
