package tech.chillo.sa.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;
import tech.chillo.sa.dto.ErrorEntity;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody ErrorEntity handleException(EntityNotFoundException exception) {
        return new ErrorEntity(null, exception.getMessage());
    }
    @ResponseStatus(CONFLICT)
    @ExceptionHandler({RuntimeException.class})
    public @ResponseBody ErrorEntity handleRuntimeException(RuntimeException exception) {
        return new ErrorEntity(null, exception.getMessage());
    }
    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler({MethodNotAllowedException.class})
    public @ResponseBody ErrorEntity handleMethodNotAllowedRxception(MethodNotAllowedException exception) {
        return new ErrorEntity(null, exception.getMessage());
    }
}
