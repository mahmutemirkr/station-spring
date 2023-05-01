package com.mekstart.controller;

import com.mekstart.domain.Bus;
import com.mekstart.dto.BusDTO;
import com.mekstart.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<List<Bus>> getAll(){
        List<Bus> buses = busService.getAll();
        return ResponseEntity.ok(buses);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> createBus(@Valid @RequestBody Bus bus){

        busService.createBus(bus);

        Map<String,String> map = new HashMap<>();
        map.put("message","Bus is created successfully");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteBus(@PathVariable("id") Long id){

        busService.deleteBus(id);

        Map<String , String> map = new HashMap<>();
        map.put("message","Bus is deleted successfuly");
        map.put("status","true");

        return ResponseEntity.ok(map);

    }


    @PutMapping("{id}")
    public ResponseEntity<Map<String,String>> updateBus(@PathVariable("id") Long id, @Valid
    @RequestBody BusDTO busDTO) {

        busService.updateBus(id,busDTO);

        Map<String,String> map = new HashMap<>();
        map.put("message","Bus is updated successfuly");
        map.put("status" ,"true");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
