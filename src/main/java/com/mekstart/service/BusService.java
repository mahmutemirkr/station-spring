package com.mekstart.service;

import com.mekstart.domain.Bus;
import com.mekstart.dto.BusDTO;
import com.mekstart.exception.ConflictException;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {


    @Autowired
    private BusRepository busRepository;
    public List<Bus> getAll() {

        return busRepository.findAll();

    }

    public void deleteBus(Long id) {

        Bus bus = findBus(id);
        busRepository.delete(bus);

    }


    public Bus findBus(Long id){

        return busRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Bus not found with id:" + id));

    }

    public void createBus(Bus bus) {

        if(busRepository.existsById(bus.getId())){

            throw new ConflictException("Email is already exist");

        }
        busRepository.save(bus);
    }


    public void updateBus(Long id, BusDTO busDTO) {

        boolean existId = busRepository.existsById(busDTO.getId());
        Bus bus = findBus(id);

        if( existId && !busDTO.getId().equals(bus.getId()) ) {
            throw new ConflictException("Id is already exist ");
        }

        bus.setBusName(busDTO.getBusName());
        bus.setRota(busDTO.getRota());

        busRepository.save(bus);

    }

}
