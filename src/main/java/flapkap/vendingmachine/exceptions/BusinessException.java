package flapkap.vendingmachine.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BusinessException extends RuntimeException{
    private final String code;
    private final HttpStatus httpStatus;
    private final List<String> error;

    private BusinessException(final String message, final String code, final HttpStatus httpStatus, final List<String> error) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public static BusinessException of(final String message) {
        return new BusinessException(message, null, null, null);
    }

    public static BusinessException of(final String message, final String code) {
        return new BusinessException(message, code, null, null);
    }

    public static BusinessException of(final String message, final String code, final HttpStatus httpStatus) {
        return new BusinessException(message, code, httpStatus, null);
    }

    public static BusinessException badRequest(final String message, final String code) {
        return of(message, code, HttpStatus.BAD_REQUEST);
    }

    public static BusinessException internalServerError(final String message, final String code) {
        return of(message, code, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static BusinessException unAuthorizedRequest(final String message, final String code) {
        return of(message, code, HttpStatus.UNAUTHORIZED);
    }

    public static BusinessException errorList(final String message, final String code, final HttpStatus httpStatus, final List<String> error) {
        return new BusinessException(message, code, httpStatus, error);
    }

    public String getCode() {
        return this.code;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public List<String> getError() {
        return this.error;
    }
}
