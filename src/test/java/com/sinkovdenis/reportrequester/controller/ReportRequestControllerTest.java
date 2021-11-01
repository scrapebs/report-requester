package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.model.ReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestControllerTest {

    @Mock
    private ReportRequestService service;

    @Mock
    private ReportRequest reportRequest;

    @InjectMocks
    private ReportRequestController controller;

    @Test
    public void testRequest() {
        controller.request(reportRequest);
        verify(service).request(reportRequest);
    }

}
