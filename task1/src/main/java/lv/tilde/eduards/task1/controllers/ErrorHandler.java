package lv.tilde.eduards.task1.controllers;

import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.exceptions.ApplicationError;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ErrorHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ApplicationError> handleCustomException(CustomBadRequestException exception, WebRequest webRequest) {
        ApplicationError error = new ApplicationError();
        error.setStatus(ResponseStatus.FAIL);
        error.setReason(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
