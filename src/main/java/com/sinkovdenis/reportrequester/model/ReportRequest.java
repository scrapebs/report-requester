package com.sinkovdenis.reportrequester.model;

import lombok.Data;

@Data
public class ReportRequest {

    private DbType dbType;
    private String dbName;
    private String queryText;
    private String email;

    public enum DbType {
        POSTGRESQL, ORACLE
    }
}
