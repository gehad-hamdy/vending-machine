package flapkap.vendingmachine.api;

import cz.jirutka.rsql.parser.RSQLParserException;
import flapkap.vendingmachine.api.v1.resources.ErrorResource;
import flapkap.vendingmachine.exceptions.BusinessException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ErrorResource> handleNoSuchElementException(final NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResource().message("Item not found"));
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    ResponseEntity<ErrorResource> handleInvalidDataAccessApiUsageException(final InvalidDataAccessApiUsageException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResource().message("Request data is not valid").details(e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ErrorResource> handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResource().message(e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorResource> handleBusinessException(final BusinessException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResource().message(e.getMessage()).code(e.getCode()));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    ResponseEntity<ErrorResource> handlePropertyReferenceException(final PropertyReferenceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResource().message(e.getMessage()));
    }

    @ExceptionHandler(RSQLParserException.class)
    ResponseEntity<ErrorResource> handleRSQLParserException(final RSQLParserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResource().message("Error parsing filter RSQL").details(e.getMessage()));
    }

    @ExceptionHandler(BeanCreationException.class)
    ResponseEntity<ErrorResource> handleBeanCreationException(final BeanCreationException e) {
        if (e.getRootCause() instanceof BusinessException) {
            return handleBusinessException((BusinessException) e.getRootCause());
        }
        throw new RuntimeException(e);
    }
}
