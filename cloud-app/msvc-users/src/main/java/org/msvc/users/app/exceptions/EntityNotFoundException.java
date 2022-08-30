package org.msvc.users.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
    private String errorCode;

    public EntityNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
