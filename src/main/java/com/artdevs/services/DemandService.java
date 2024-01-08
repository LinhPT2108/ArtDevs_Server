package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.Demand;

public interface DemandService {
    Demand findDemandById(Integer demandId);

    List<Demand> findAll();

    Demand saveDemand(Demand demand);

    Demand updateDemand(Demand demand);

    void deleteDemand(Demand demand);
}
