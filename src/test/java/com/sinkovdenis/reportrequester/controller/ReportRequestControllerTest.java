package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequesterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportRequestControllerTest {

    @Mock
    private ReportRequesterService service;

    @Mock
    private ByDateReportRequest byDateReportRequest;

    @Mock
    private ByIdsReportRequest byIdsReportRequest;

    @InjectMocks
    private ReportRequestController controller;

    @Test
    void testReportTypes() {
        controller.reportTypes();
        verify(service).showReportTypes();
    }

    @Test
    void testRequest_byDate() {
        controller.request(byDateReportRequest);
        verify(service).requestReport(byDateReportRequest);
    }

    @Test
    void testRequest_byIds() {
        controller.request(byIdsReportRequest);
        verify(service).requestReport(byIdsReportRequest);
    }

}
