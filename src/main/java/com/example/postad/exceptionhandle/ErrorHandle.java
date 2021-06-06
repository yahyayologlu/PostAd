package com.example.postad.exceptionhandle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandle extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ErrorHandle.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> customHandleNotFound(Exception ex, HttpServletRequest request) {
        Error errors = new Error();
        errors.setTimestamp(LocalDateTime.now());
        List<String> errorList = new ArrayList<>();
        errorList.add(ex.getMessage());
        errors.setErrors(errorList);
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        logger.error("User: ".concat(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : String.valueOf(request.getRemoteUser())) + " -RemoteAddr : ".concat(String.valueOf(request.getRemoteAddr())).concat(" -RemoteHost : ").concat(String.valueOf(request.getRemoteHost())) + " -URI : ".concat(request.getRequestURI()) +" -Error List : " + errorList);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    // @Validate For Validating Path Variables and Request Parameters
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    // error handle for @Valid
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Error error = new Error();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(status.value());
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(m -> errors.add(m.getField() + " : " + m.getDefaultMessage()));
        error.setErrors(errors);
        return new ResponseEntity<>(error, headers, status);
    }

    @Getter
    @Setter
    public static class Error {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime timestamp;
        private int status;
        private List<String> errors;
    }

}
