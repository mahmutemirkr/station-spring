package com.mekstart.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {

    @Setter(AccessLevel.NONE)
    private Long id;
    @NotNull(message = "first name can not be null")
    @NotBlank(message = "fist name can not be white space")
    private String busName;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "fist name can not be white space")
    private String rota;


}
