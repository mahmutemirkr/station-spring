package com.mekstart.mapper;

import com.mekstart.domain.Station;
import com.mekstart.dto.StationDTO;
import com.mekstart.dto.request.StationRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {

    Station stationRequestToStation(StationRequest stationRequest);


    StationDTO stationToStationDTO(Station station);

    List<StationDTO> map(List<Station> stationList);
}
