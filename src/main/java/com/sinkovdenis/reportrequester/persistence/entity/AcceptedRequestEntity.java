package com.sinkovdenis.reportrequester.persistence.entity;

import com.sinkovdenis.reportrequester.model.ReportType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"requestId"})
@Table(name = "ACCEPTED_REQUEST")
public class AcceptedRequestEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCEPTED_REQUEST_SEQ")
    @SequenceGenerator(name = "ACCEPTED_REQUEST_SEQ", sequenceName = "SEQ_REQUEST_ID", allocationSize = 1)
    @Column(name = "ID")
    private long requestId;
    
    @Column(name = "REPORT_TYPE")
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(name = "CREATED_BY")
    @Basic(optional = false)
    private String createdBy;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    private Date creationDate;

    @Column(name = "REQUEST_SOURCE")
    private String requestSource;
}
