package org.msvc.courses.coursesmsvc.controllers;

import org.msvc.courses.coursesmsvc.exceptions.RequestException;
import org.msvc.courses.coursesmsvc.model.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, ErrorDTO>> validateExceptionHandler(MethodArgumentNotValidException exception){

        Map<String, ErrorDTO> errorDTOMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            ErrorDTO errorDTO = ErrorDTO
                    .builder()
                    .errorCode(error.getCode())
                    .errorMessage(error.getField() + " " + error.getDefaultMessage())
                    .build();
            errorDTOMap.put(error.getField(), errorDTO);
        });

        return new ResponseEntity<>(errorDTOMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> RequestExceptionHandler(RequestException exception){

        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
