package com.example.demo.customException;

import com.example.demo.dto.ApiErrorCodes;
import com.example.demo.dto.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestApiErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
        return new RestErrorMessage(
                e.getMessage(),
                ApiErrorCodes.MISSING_MEMBER.getErrorId(),
                "2c2996af-1887-4bd6-ae8b-2d41cac12b7b"
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorMessage handleRuntimeException(RuntimeException e) {
        return new RestErrorMessage(
                e.getMessage(),
                ApiErrorCodes.BAD_BACKEND.getErrorId(),
                "29cb60df-5edf-4502-9488-e1a34b90c2ca"
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestErrorMessage handleConstraintViolationException(ConstraintViolationException e) {
        var violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("|"));
        return new RestErrorMessage(violations, ApiErrorCodes.BAD_MEMBER.getErrorId(), "7c248b97-9aa8-46fd-b5b2-9f8ea537635c");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var violations = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        return new RestErrorMessage(violations, ApiErrorCodes.BAD_PARAMETER.getErrorId(), "7c248b97-9aa8-46fd-b5b2-9f8ea537635c");
    }

    @ExceptionHandler(CreditCardNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestErrorMessage handleUserHasAlreadyCardException(CreditCardNotValidException e) {
        return new RestErrorMessage(
                e.getMessage(),
                ApiErrorCodes.NOT_VALID_CARD.getErrorId(),
                "29cb60df-5edf-4502-9488-e1a34b80c45a"
        );
    }
}
