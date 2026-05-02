package ru.pet_projects.ticket_service.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.pet_projects.ticket_service.dtos.ErrorResponseDto;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto commonHandler(Exception e) {
        return new ErrorResponseDto("Internal Server Error",
                e.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto entityNotFoundHandler(EntityNotFoundException e) {
        return new ErrorResponseDto("Entity not found",
                e.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler(exception = {
            IllegalArgumentException.class,
            IllegalStateException.class,
            ValidationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto badRequestHandler(Exception e) {
        return new ErrorResponseDto("Bad Request",
                e.getMessage(),
                LocalDateTime.now());
    }

}