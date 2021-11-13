package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequesterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestControllerTest {

    @Mock
    private ReportRequesterService service;

    @Mock
    private ByDateReportRequest byDateReportRequest;

    @Mock
    private ByIdsReportRequest byIdsReportRequest;

    @InjectMocks
    private ReportRequestController controller;

    @Test
    public void testReportTypes() {
        controller.reportTypes();
        verify(service).showReportTypes();
    }

    @Test
    public void testRequest_byDate() {
        controller.request(byDateReportRequest);
        verify(service).requestReport(byDateReportRequest);
    }

    @Test
    public void testRequest_byIds() {
        controller.request(byIdsReportRequest);
        verify(service).requestReport(byIdsReportRequest);
    }

}
