package com.mekstart.mapper;

import com.mekstart.domain.Bus;
import com.mekstart.dto.BusDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BusMapperImpl implements BusMapper{


    @Override
    public BusDTO busToBusDTO(Bus bus) {
        if (bus == null) {
            return null;
        } else {
            BusDTO busDTO = new BusDTO();
            busDTO.setBusName(bus.getBusName());
            busDTO.setRota(bus.getRota());
            return busDTO;
        }
    }

    @Override
    public List<BusDTO> map(List<Bus> busList) {

        if (busList == null) {
            return null;
        } else {
            List<BusDTO> list = new ArrayList(busList.size());
            Iterator var = busList.iterator();

            while(var.hasNext()) {
                Bus bus = (Bus) var.next();
                list.add(this.busToBusDTO(bus));
            }

            return list;
        }
    }
}
