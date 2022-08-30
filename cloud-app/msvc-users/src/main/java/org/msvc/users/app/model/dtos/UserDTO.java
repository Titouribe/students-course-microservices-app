package org.msvc.users.app.model.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class UserDTO {

    private Long userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String userEmail;

    private Date dateCreated;

    @NotBlank
    private String password;
}
