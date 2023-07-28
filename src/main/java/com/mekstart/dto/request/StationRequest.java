package com.mekstart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationRequest {

    @NotNull(message = "Station name can not be null")
    @Size(min = 2,max = 80,message = "Station name '${validateValue}' should be between {min} and {max}")
    private String stationName;

    @NotBlank
    @NotNull(message = "Rota can not be null")
    private String rota;

    @NotNull(message = "Bus ID can not be null")
    private Long busId;

}
