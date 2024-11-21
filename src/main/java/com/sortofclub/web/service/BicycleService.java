package com.sortofclub.web.service;

import com.sortofclub.web.dto.BicycleCreationDto;
import com.sortofclub.web.dto.BicycleDto;
import com.sortofclub.web.models.Bicycle;

import java.util.List;
import java.util.Optional;

public interface BicycleService {
    List<BicycleDto> findAllBicycles();

    Bicycle saveBicycle(BicycleCreationDto bicycleCreationDto);

    Bicycle update(BicycleDto bicycleDto, long id);

    Optional<Bicycle> delete(long id);
}
