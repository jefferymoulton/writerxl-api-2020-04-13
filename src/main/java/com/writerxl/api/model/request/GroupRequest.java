package com.writerxl.api.model.request;


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
public class GroupRequest {

    @ApiModelProperty
    @NotNull
    private String name;

    @ApiModelProperty
    @NotNull
    private String description;
}
