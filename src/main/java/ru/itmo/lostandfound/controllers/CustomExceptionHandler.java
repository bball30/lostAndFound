package ru.itmo.lostandfound.controllers;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.lostandfound.exceptions.*;
import ru.itmo.lostandfound.messages.ExceptionResponse;

import java.util.StringJoiner;

/**
 * Обработчик всех кастомных исключений приложения
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик исключений, порождающих статус 409 CONFLICT
     *
     * @param e пойманное исключение
     * @return <code>ResponseEntity</code> с кодом ответа 409
     */
    @ExceptionHandler({
            CategoryAlreadyExistsException.class,
            LossPlaceAlreadyExistsException.class,
            CampusAlreadyExistsException.class,
            PhotoAlreadyUsedException.class,
            ItemStatusConflictException.class,
            PhotoBelongsAnotherUserException.class
    })
    public ResponseEntity<Object> handleConflictException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.CONFLICT);
    }

    /**
     * Обработчик исключений, порождающих статус 404 NOT_FOUND
     *
     * @param e пойманное исключение
     * @return <code>ResponseEntity</code> с кодом ответа 404
     */
    @ExceptionHandler({
            NoSuchCampusException.class,
            NoSuchCategoryException.class,
            NoSuchLossPlaceException.class,
            NoSuchItemException.class,
            NoSuchPhotoException.class,
            NoSuchUserException.class
    })
    public ResponseEntity<Object> handleNotFoundException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик исключений, порождающих статус 400 BAD_REQUEST
     *
     * @param e пойманное исключение
     * @return <code>ResponseEntity</code> с кодом ответа 400
     */
    @ExceptionHandler({
            IllegalItemStatusException.class,
            IllegalLossTimeException.class,
            UploadPhotoException.class
    })
    public ResponseEntity<Object> handleBadRequestException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик исключений, порождающих статус 503 SERVICE_UNAVAILABLE
     *
     * @param e пойманное исключение
     * @return <code>ResponseEntity</code> с кодом ответа 503
     */
    @ExceptionHandler(ObjectStorageException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Обработчик ошибок встроенной валидации запросов
     *
     * @param e пойманное исключение
     * @return <code>ResponseEntity</code> с кодом ответа 400
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
        StringJoiner joiner = new StringJoiner(",");
        e.getConstraintViolations().stream().map(c -> c.getPropertyPath().toString()).forEach(joiner::add);
        return handleExceptionInternal(new RuntimeException(joiner.toString(), e), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex, Object body, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode, @NonNull WebRequest request
    ) {
        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
