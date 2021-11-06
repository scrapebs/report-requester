package com.sinkovdenis.reportrequester.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GenericReportRequest {

    @NonNull
    private ReportType reportType;
    @NonNull
    private String email;
}
