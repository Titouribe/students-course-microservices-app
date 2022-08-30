package org.msvc.users.app.controllers;

import org.msvc.users.app.constants.ErrorsConstants;
import org.msvc.users.app.exceptions.EntityNotFoundException;
import org.msvc.users.app.model.dtos.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, ErrorDTO>> validateExceptionHandler(MethodArgumentNotValidException exception){

        Map<String, ErrorDTO> errorDTOMap = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            ErrorDTO errorDTO = ErrorDTO
                    .builder()
                    .errorCode(error.getCode())
                    .errorMessage(error.getDefaultMessage())
                    .build();
            errorDTOMap.put(error.getCode(), errorDTO);
        });

        return new ResponseEntity<>(errorDTOMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> EntityNotFoundExceptionHandler(EntityNotFoundException exception){

        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Map<String, ErrorDTO>> constraintExceptionHandler(ConstraintViolationException exception){

        Map<String, ErrorDTO> errorDTOMap = new HashMap<>();

        exception.getConstraintViolations().forEach(error -> {
            ErrorDTO errorDTO = ErrorDTO
                    .builder()
                    .errorCode(error.getPropertyPath().toString())
                    .errorMessage(error.getMessageTemplate())
                    .build();
            errorDTOMap.put(error.getMessage(), errorDTO);
        });

        return new ResponseEntity<>(errorDTOMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception){

        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(ErrorsConstants.BAD_REQUEST)
                .errorMessage(exception.getRootCause().getLocalizedMessage())
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
