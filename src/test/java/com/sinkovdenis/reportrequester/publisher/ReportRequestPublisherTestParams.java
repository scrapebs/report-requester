package com.sinkovdenis.reportrequester.publisher;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportRequestPublisherTestParams {

    public static final String REPORT_REQUEST_TOPIC = "reports.requests";
    public static final String ANY_GROUP = "groupId";
}
