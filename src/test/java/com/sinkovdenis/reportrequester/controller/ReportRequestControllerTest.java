package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.model.GenericReportRequest;
import com.sinkovdenis.reportrequester.model.ReportType;
import com.sinkovdenis.reportrequester.service.ReportRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestControllerTest {

    @Mock
    private ReportRequestService service;

    @Mock
    private ByDateReportRequest byDateReportRequest;

    @Mock
    private ByIdsReportRequest byIdsReportRequest;

    @InjectMocks
    private ReportRequestController controller;

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
