package com.sinkovdenis.reportrequester.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GenericReportRequesterException extends RuntimeException {
    private static final long serialVersionUID = 7848351942375646635L;

    @Getter
    @JsonIgnore
    protected int httpCode;

    public GenericReportRequesterException(String message, Throwable error) {
        super(message, error);
        httpCode = ErrorCodes.GENERIC_ERROR_CODE;
    }

    public GenericReportRequesterException(Throwable error) {
        super(error);
        httpCode = ErrorCodes.GENERIC_ERROR_CODE;
    }

    public GenericReportRequesterException(String message) {
        super(message);
        httpCode = ErrorCodes.GENERIC_ERROR_CODE;
    }

    public GenericReportRequesterException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
    }
}
