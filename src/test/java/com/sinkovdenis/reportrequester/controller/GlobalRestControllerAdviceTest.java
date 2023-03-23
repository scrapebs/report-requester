package com.sinkovdenis.reportrequester.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinkovdenis.reportrequester.GenericTestWithoutKafka;
import com.sinkovdenis.reportrequester.exception.IllegalRequestException;
import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.service.ReportRequesterService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

import static com.sinkovdenis.reportrequester.JsonHelper.toJson;
import static com.sinkovdenis.reportrequester.TestData.createByDateReportRequest;
import static com.sinkovdenis.reportrequester.exception.ErrorCodes.GENERIC_ERROR_CODE;
import static com.sinkovdenis.reportrequester.exception.ErrorCodes.ILLEGAL_REQUEST;
import static com.sinkovdenis.reportrequester.exception.ErrorMessages.ILLEGAL_REQUEST_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
class GlobalRestControllerAdviceTest extends GenericTestWithoutKafka {

    @Autowired
    public MockMvc mockMvc;
    
    @InjectMocks
    private ReportRequestController reportRequestController;
    
    @Mock
    private ReportRequesterService service;
    
    private ByDateReportRequest request;
    
    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(reportRequestController)
                .setControllerAdvice(new GlobalRestControllerAdvice())
                .build();
        request = createByDateReportRequest();
    }
    
    @Test
    @SneakyThrows
    void testOnError_ConstraintViolationException() {
        doThrow(new ConstraintViolationException(Collections.emptySet())).when(service).requestReport(any());

        MockHttpServletResponse response = mockMvc.perform(
                post("/request/by-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(GENERIC_ERROR_CODE);    
        assertThat(response.getContentAsString()).contains("ConstraintViolationException occurred");
    }

    @Test
    @SneakyThrows
    void testOnError_IllegalRequestException() {
        doThrow(new IllegalRequestException("error")).when(service).requestReport(any());

        MockHttpServletResponse response = mockMvc.perform(
                post("/request/by-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(ILLEGAL_REQUEST);
        assertThat(response.getContentAsString()).contains(ILLEGAL_REQUEST_MESSAGE);
    }
}
