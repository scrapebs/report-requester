CREATE TABLE IF NOT EXISTS ACCEPTED_REQUEST
(
    ID BIGINT NOT NULL,
    REPORT_TYPE CHARACTER VARYING(40) NOT NULL,
    CREATED_BY CHARACTER VARYING(100) NOT NULL,
    CREATION_DATE TIMESTAMP without time zone NOT NULL,
    REQUEST_SOURCE CHARACTER VARYING(1000),
    CONSTRAINT PK_ACCEPTED_REQUEST_ID PRIMARY KEY (ID)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_REQUEST_ID;

CREATE INDEX IF NOT EXISTS ACCEPTED_REQUEST_IDX
    ON ACCEPTED_REQUEST USING btree
    (REPORT_TYPE, CREATION_DATE);
