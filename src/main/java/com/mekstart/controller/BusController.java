package com.mekstart.controller;

import com.mekstart.domain.Bus;
import com.mekstart.dto.BusDTO;
import com.mekstart.dto.request.BusRequest;
import com.mekstart.dto.response.BusResponse;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.exception.message.ErrorMessage;
import com.mekstart.service.BusService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,String>> createBus(@Valid @RequestBody BusRequest busRequest){

        busService.createBus(busRequest);

        Map<String,String> map = new HashMap<>();
        map.put("message","Bus is created successfully");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable("id") Long id){

        Bus bus = busService.getBusById(id);

        return ResponseEntity.ok(bus);
    }

    @GetMapping("/all")
    public List<Bus> getAllBus(){

        return busService.getAllBuses();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Bus>> getAllBusWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction", required = false, defaultValue = "DESC")Sort.Direction direction){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction,prop));

        Page<Bus> busPage = busService.getAllWithPage(pageable);

        return ResponseEntity.ok(busPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBus(@PathVariable("id") Long id, @Valid @RequestBody BusRequest busRequest){

        busService.updateBus(id,busRequest);

        String success = "Bus Updated Successfully";

        return ResponseEntity.ok(success);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteBus(@PathVariable("id") Long id){

        try {

            busService.deleteBus(id);

            Map<String , String> map = new HashMap<>();
            map.put("message","Bus is deleted successfuly");
            map.put("status","true");

            return ResponseEntity.ok(map);

        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION);
        }

    }

}
