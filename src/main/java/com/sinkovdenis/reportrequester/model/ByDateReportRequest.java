package com.sinkovdenis.reportrequester.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

import static com.sinkovdenis.reportrequester.model.FormatPatterns.DATE_TIME;

@SuperBuilder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ByDateReportRequest extends GenericReportRequest {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Date from;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Date to;
}
