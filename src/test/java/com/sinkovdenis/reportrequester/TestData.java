package com.sinkovdenis.reportrequester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportrequester.model.ReportType;
import com.sinkovdenis.reportrequester.model.request.GenericReportRequest;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestData {

    public static final Date DATE_FROM = Date.from(LocalDateTime.of(2021, 07, 01, 10, 0, 0)
            .atZone(ZoneId.of("UTC")).toInstant());
    public static final Date DATE_TO = Date.from(LocalDateTime.of(2021, 07, 02, 10, 0, 0)
            .atZone(ZoneId.of("UTC")).toInstant());
    public static List<String> REQUESTED_IDS = Arrays.asList("1111", "2222", "3333");
    public static String EMAIL_FOR_RESPONSE = "email@gmail.com";

    public static ByDateReportRequest createByDateReportRequest(ReportType reportType) {
        return ByDateReportRequest.builder()
                .reportType(reportType)
                .from(DATE_FROM)
                .to(DATE_TO)
                .email(EMAIL_FOR_RESPONSE)
                .build();
    }

    public static ByIdsReportRequest createByIdsReportRequest(ReportType reportType) {
        return ByIdsReportRequest.builder()
                .reportType(reportType)
                .ids(REQUESTED_IDS)
                .email(EMAIL_FOR_RESPONSE)
                .build();
    }

    public static AcceptedRequestEntity createAcceptedRequestEntity() {
        AcceptedRequestEntity entity = new AcceptedRequestEntity();
        entity.setRequestSource("source");
        entity.setCreationDate(new Date());
        entity.setReportType(ReportType.ORDERS_REPORT);
        entity.setCreatedBy("creator");
        return entity;
    }
    
    public static ByDateReportRequest createByDateReportRequest() {
        ByDateReportRequest request = new ByDateReportRequest();
        request.setRequestId(100L);
        request.setReportType(ReportType.ORDERS_REPORT);
        request.setFrom(DATE_FROM);
        request.setTo(DATE_TO);
        request.setEmail("email");
        return request;
    }
    
}
