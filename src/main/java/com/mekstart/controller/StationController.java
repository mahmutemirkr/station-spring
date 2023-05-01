package com.mekstart.controller;

import com.mekstart.domain.Bus;
import com.mekstart.domain.Station;
import com.mekstart.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping("/add")
    public ResponseEntity<Station> addStation(@RequestParam Long busId, @RequestBody Station station) {
        Station savedStation = stationService.createStation(busId, station);
        return ResponseEntity.ok(savedStation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station station) {
        Station updatedStation = stationService.updateStation(id, station);
        return ResponseEntity.ok(updatedStation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Station>> getAll(){
        List<Station> buses = stationService.getAll();
        return ResponseEntity.ok(buses);
    }



}
