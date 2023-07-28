package com.mekstart.service;

import com.mekstart.domain.Bus;
import com.mekstart.domain.Station;
import com.mekstart.dto.request.BusRequest;
import com.mekstart.dto.response.StationResponse;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.exception.message.ErrorMessage;
import com.mekstart.mapper.BusMapper;
import com.mekstart.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BusService {

    private final BusRepository busRepository;


    private final BusMapper busMapper;

    @Autowired
    public BusService(BusRepository busRepository, BusMapper busMapper) {
        this.busRepository = busRepository;
        this.busMapper = busMapper;
        ;
    }

    public Bus getBus(Long id){
        Bus bus = busRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION, id)));

        return bus;
    }

    public void createBus(BusRequest busRequest) {

        Bus bus = new Bus();
        bus.setBusName(busRequest.getBusName());
        bus.setRota(busRequest.getRota());

        busRepository.save(bus);

    }

    public Bus getBusById(Long id) {

        Bus bus = getBus(id);
        return bus;

    }

    public List<Bus> getAllBuses() {

        List<Bus> buses = busRepository.findAll();

        return  buses;
    }

    private StationResponse convertToStationResponse(Station station) {
        StationResponse stationResponse = new StationResponse();
        stationResponse.setId(station.getId());
        stationResponse.setStationName(station.getStationName());
        stationResponse.setRota(station.getRota());
        return stationResponse;
    }

    public Page<Bus> getAllWithPage(Pageable pageable) {

        Page<Bus> busPage = busRepository.findAll(pageable);

        return busPage;
    }

    public void updateBus(Long id, BusRequest busRequest) {

        Bus bus = getBus(id);
        bus.setBusName(busRequest.getBusName());
        bus.setRota(busRequest.getRota());
        busRepository.save(bus);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBus(Long id) {

        Bus bus = getBus(id);
        busRepository.delete(bus);

    }


}
