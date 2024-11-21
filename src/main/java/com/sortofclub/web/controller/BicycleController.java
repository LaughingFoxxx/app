package com.sortofclub.web.controller;

import com.sortofclub.web.dto.BicycleCreationDto;
import com.sortofclub.web.dto.BicycleDto;
import com.sortofclub.web.models.Bicycle;
import com.sortofclub.web.service.BicycleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BicycleController {
    private BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @GetMapping("/bicycles")
    public List<BicycleDto> showAllBicycles() {
        return bicycleService.findAllBicycles();
    }

    @PostMapping("/bicycles/new")
    public BicycleCreationDto addNewBicycle(@RequestBody BicycleCreationDto bicycleCreationDto) {
        bicycleService.saveBicycle(bicycleCreationDto);
        return bicycleCreationDto;
    }

    @PutMapping("/bicycles/update/{id}")
    public BicycleDto updateABicycle(@PathVariable("id") long id, @RequestBody BicycleDto bicycleDto) {
        bicycleService.update(bicycleDto, id);
        return bicycleDto;
    }

    @DeleteMapping("/bicycles/delete/{id}")
    public Optional<Bicycle> deleteABicycle(@PathVariable("id") long id) {
        return bicycleService.delete(id);
    }
}
