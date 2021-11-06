package com.sinkovdenis.reportrequester.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

import static com.sinkovdenis.reportrequester.model.FormatPatterns.DATE_TIME;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ByDateReportRequest extends GenericReportRequest {
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Date from;
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Date to;
}
