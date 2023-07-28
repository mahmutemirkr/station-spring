package com.mekstart.mapper;

import com.mekstart.domain.Station;
import com.mekstart.dto.StationDTO;
import com.mekstart.dto.request.StationRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationMapperImpl implements StationMapper{
    @Override
    public Station stationRequestToStation(StationRequest stationRequest) {
        return null;
    }

    @Override
    public StationDTO stationToStationDTO(Station station) {
        return null;
    }

    @Override
    public List<StationDTO> map(List<Station> stationList) {
        return null;
    }
}
