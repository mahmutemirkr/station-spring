package com.mekstart.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {


    private String busName;


    private String rota;

    private List<StationDTO> stations = new ArrayList<>();

}
