package com.sinkovdenis.reportrequester.model.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ByIdsReportRequest extends GenericReportRequest {
    @NonNull
    private List<String> ids;
}
