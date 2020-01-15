package com.writerxl.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty
    @NotNull
    private String userKey;

    @ApiModelProperty
    @NotNull
    private String email;

    @ApiModelProperty
    @NotNull
    private String firstName;

    @ApiModelProperty
    @NotNull
    private String lastName;

    @ApiModelProperty
    @NotNull
    private UserStatus status;

}
