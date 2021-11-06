package com.sinkovdenis.reportrequester.service;

import com.sinkovdenis.reportrequester.model.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.publisher.ReportRequestPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequesterServiceTest {

    @Mock
    private ReportRequestPublisher publisher;

    @Mock
    private ByDateReportRequest byDateReportRequest;

    @Mock
    private ByIdsReportRequest byIdsReportRequest;

    @InjectMocks
    private ReportRequesterService service;

    @Test
    public void testRequest_byDate() {
        service.requestReport(byDateReportRequest);
        verify(publisher).publish(byDateReportRequest);
    }

    @Test
    public void testRequest_byIds() {
        service.requestReport(byIdsReportRequest);
        verify(publisher).publish(byIdsReportRequest);
    }
}
