package com.mekstart.dto;

import com.mekstart.domain.Bus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {

    private String stationName;

    private String rota;

    private Bus bus;

}
