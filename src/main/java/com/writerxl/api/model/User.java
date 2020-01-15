package com.writerxl.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull
    private String userKey;

    @NotNull
    private String emailAddress;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private UserStatus status;

}
