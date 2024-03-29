package com.artdevs.services.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.DemandRepository;
import com.artdevs.services.DemandService;
@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    DemandRepository demandRepository;

    @Override
    public Demand findDemandById(Integer demandId) {
        Optional<Demand> demandOptional = demandRepository.findById(demandId);
        return demandOptional.orElse(null);
    }

    @Override
    public List<Demand> findAll() {
        return demandRepository.findAll();
    }

    @Override
    public Demand saveDemand(Demand demand) {
        return demandRepository.save(demand);
    }

    @Override
    public Demand updateDemand(Demand demand) {
        return demandRepository.save(demand);
    }

    @Override
    public void deleteDemand(Demand demand) {
        demandRepository.delete(demand);
    }

	@Override
	public List<Demand> findByUser(User user) {
		// TODO Auto-generated method stub
		return demandRepository.findByUser(user);
	}

	@Override
	public Demand findByUserAndLanguage(User user, ProgramingLanguage language) {
		// TODO Auto-generated method stub
		return demandRepository.findByUserAndLanguage(user,language);
	}
}
