package com.mekstart.controller;

import com.mekstart.domain.Station;
import com.mekstart.dto.StationDTO;
import com.mekstart.dto.request.StationRequest;
import com.mekstart.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> addStation(@Valid @RequestBody StationRequest stationRequest) {

        stationService.saveStation(stationRequest);
        String success = "Station saved successfully";
        return ResponseEntity.ok(success);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") Long id){

        Station station = stationService.getStationById(id);

        return ResponseEntity.ok(station);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAllStation(){

        List<Station> stationList = stationService.getAllStation();

        return ResponseEntity.ok(stationList);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<Station>> getAllStationWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "directon",required = false,defaultValue = "DESC")  Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<Station> stationPage = stationService.getAllWithPage(pageable);
        return ResponseEntity.ok(stationPage);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateStation(@PathVariable Long id, @Valid @RequestBody StationRequest stationRequest) {

        stationService.updateStation(id,stationRequest);

        String success = "Station updated successfully";

        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable("id") Long id) {

        stationService.removeStation(id);

        String successMessage = "Station deleted successfully";

        return ResponseEntity.ok(successMessage);

    }



}
