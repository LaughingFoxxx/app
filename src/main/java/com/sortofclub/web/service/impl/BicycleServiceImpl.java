package com.sortofclub.web.service.impl;

import com.sortofclub.web.dto.BicycleCreationDto;
import com.sortofclub.web.dto.BicycleDto;
import com.sortofclub.web.models.Bicycle;
import com.sortofclub.web.repository.BicycleRepository;
import com.sortofclub.web.service.BicycleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BicycleServiceImpl implements BicycleService {
    private BicycleRepository bicycleRepository;

    public BicycleServiceImpl(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public Bicycle saveBicycle(BicycleCreationDto bicycleCreationDto) {
        Bicycle bicycle = mapToBicycle(bicycleCreationDto);
        bicycleRepository.save(bicycle);
        return bicycle;
    }

    @Override
    public Bicycle update(BicycleDto bicycleDto, long id) {
        Bicycle inputBicycle = mapToBicycle(bicycleDto);
        Optional<Bicycle> foundBicycle = bicycleRepository.findById(id);
        Bicycle bicycle;
        if (foundBicycle.isPresent()) {
            bicycle = foundBicycle.get();
            bicycle.setName(inputBicycle.getName());
            bicycle.setDescription(inputBicycle.getDescription());
            bicycle.setPhotoUrl(inputBicycle.getPhotoUrl());
            bicycleRepository.save(bicycle);
        } else {
            throw new RuntimeException("Bicycle not found with id " + id);
        }
        return bicycle;
    }

    @Override
    public Optional<Bicycle> delete(long id) {
        Optional<Bicycle> foundBicycle = bicycleRepository.findById(id);
        if (foundBicycle.isPresent()) {
            bicycleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Bicycle not found with id " + id);
        }
        return foundBicycle;
    }

    @Override
    public List<BicycleDto> findAllBicycles() {
        List<Bicycle> bicycles = bicycleRepository.findAll();
        return bicycles.stream().map(this::mapToBicycleDto).collect(Collectors.toList());
    }

    private Bicycle mapToBicycle(BicycleCreationDto bicycleCreationDto) {
        Bicycle bicycle = Bicycle.builder()
                .name(bicycleCreationDto.getName())
                .description(bicycleCreationDto.getDescription())
                .photoUrl(bicycleCreationDto.getPhotoUrl())
                .build();
        return bicycle;
    }

    private Bicycle mapToBicycle(BicycleDto bicycleDto) {
        Bicycle bicycle = Bicycle.builder()
                .name(bicycleDto.getName())
                .description(bicycleDto.getDescription())
                .photoUrl(bicycleDto.getPhotoUrl())
                .build();
        return bicycle;
    }

    private BicycleDto mapToBicycleDto(Bicycle bicycle) {
        BicycleDto bicycleDto = BicycleDto.builder()
                .id(bicycle.getId())
                .name(bicycle.getName())
                .description(bicycle.getDescription())
                .photoUrl(bicycle.getPhotoUrl())
                .build();
        return bicycleDto;
    }
}
