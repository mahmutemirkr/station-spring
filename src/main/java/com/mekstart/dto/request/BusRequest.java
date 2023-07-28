package com.mekstart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "fist name can not be white space")
    private String busName;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "fist name can not be white space")
    private String rota;

}
