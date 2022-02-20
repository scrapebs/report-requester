package com.sinkovdenis.reportrequester.model.request;

import com.sinkovdenis.reportrequester.model.ReportType;
import lombok.*;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AcceptedRequest {
    @NonNull
    private ReportType reportType;
    @NonNull
    private String email;
    @NonNull
    private Long requestId;
    @NonNull
    private Date creationDate;
    private String requestSource;
}
