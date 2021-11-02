package com.sinkovdenis.reportrequester.exception;

import com.sinkovdenis.reportrequester.model.GenericReportRequest;

import static com.sinkovdenis.reportrequester.exception.ErrorMessages.ILLEGAL_REQUEST_MESSAGE;

public class IllegalRequestException extends GenericReportRequesterException {
    private static final long serialVersionUID = 862155475942359425L;

    public IllegalRequestException(String message) {
        super(ILLEGAL_REQUEST_MESSAGE + message, ErrorCodes.ILLEGAL_REQUEST);
    }

    public IllegalRequestException(GenericReportRequest request) {
        super(ILLEGAL_REQUEST_MESSAGE + request.toString(), ErrorCodes.ILLEGAL_REQUEST);
    }

    public IllegalRequestException() {
        super(ILLEGAL_REQUEST_MESSAGE, ErrorCodes.ILLEGAL_REQUEST);
    }
}
