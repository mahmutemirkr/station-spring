package com.mekstart.mapper;

import com.mekstart.domain.Bus;
import com.mekstart.dto.BusDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusMapper {

    @Autowired
    BusDTO busToBusDTO(Bus bus);

    @Autowired
    List<BusDTO> map(List<Bus> busList);
}
