package org.msvc.users.app.model.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String errorCode;
    private String errorMessage;
}
