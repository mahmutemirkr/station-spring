package com.mekstart.service;

import com.mekstart.domain.Bus;
import com.mekstart.domain.Station;
import com.mekstart.dto.StationDTO;
import com.mekstart.dto.request.StationRequest;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.exception.message.ErrorMessage;
import com.mekstart.mapper.StationMapper;
import com.mekstart.repository.BusRepository;
import com.mekstart.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StationService {

    @Autowired
    private final StationRepository stationRepository;

    @Autowired
    private final StationMapper stationMapper;

    @Autowired
    private final BusRepository busRepository;

    @Autowired
    private final BusService busService;
    public StationService(StationRepository stationRepository, StationMapper stationMapper, BusRepository busRepository, BusService busService) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
        this.busRepository = busRepository;
        this.busService = busService;
    }

    private Station getStation(Long id) {

        Station station = stationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id)));

        return station;

    }


    public void saveStation(StationRequest stationRequest) {

        Bus bus = busService.getBus(stationRequest.getBusId());

        Station station = new Station();

        station.setStationName(stationRequest.getStationName());
        station.setRota(stationRequest.getRota());
        station.setBus(bus);

        stationRepository.save(station);

        //Station station = stationMapper.stationRequestToStation(stationRequest); // mapper kütüphasi
        //System.out.println(station.getId());

    }


    public Station getStationById(Long id) {

        Station station = getStation(id);

        return station;
    }


    public List<Station> getAllStation() {

        List<Station> stationList = stationRepository.findAll();

        return stationList;

    }


    public Page<Station> getAllWithPage(Pageable pageable) {

        Page<Station> stationPage = stationRepository.findAll(pageable);

        return stationPage;
    }

    public void updateStation(Long id, StationRequest stationRequest) {

        Bus bus = busService.getBus(stationRequest.getBusId());

        Station station = getStation(id);

        station.setBus(bus);
        station.setStationName(stationRequest.getStationName());
        station.setRota(stationRequest.getRota());

        stationRepository.save(station);

    }

    public void removeStation(Long id) {

        Station station = getStation(id);

        stationRepository.delete(station);

    }
}
