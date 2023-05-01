package com.mekstart.service;

import com.mekstart.domain.Bus;
import com.mekstart.domain.Station;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.repository.BusRepository;
import com.mekstart.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private BusRepository busRepository;

    public Station createStation(Long busId, Station station) {
        Bus bus = busRepository.findById(busId).orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        station.setBus(bus);
        return stationRepository.save(station);
    }

    public Station updateStation(Long id, Station station) {
        Station existingStation = stationRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Station not found"));
        existingStation.setStationName(station.getStationName());
        existingStation.setBus(station.getBus());
        return stationRepository.save(existingStation);
    }

    public void deleteStation(Long id) {
        Station station = stationRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Station not found"));
        stationRepository.delete(station);
    }

    public List<Station> getAll() {

        return stationRepository.findAll();

    }
}
