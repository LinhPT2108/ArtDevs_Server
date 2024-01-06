package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.repositories.user.TransitioninfoRepository;
import com.artdevs.services.TransitionInfoService;

public class TransitionInfoServiceImpl implements TransitionInfoService {

    @Autowired
    TransitioninfoRepository transitioninfoRepository;

    @Override
    public TransitionInfo findTransitionInfoById(String transitionInfoId) {
        Optional<TransitionInfo> transitionInfoOptional = transitioninfoRepository
                .findById(transitionInfoId);
        return transitionInfoOptional.orElse(null);
    }

    @Override
    public List<TransitionInfo> findAll() {
        return transitioninfoRepository.findAll();
    }

    @Override
    public TransitionInfo saveTransitionInfo(TransitionInfo transitionInfo) {
        return transitioninfoRepository.save(transitionInfo);
    }

    @Override
    public TransitionInfo updateTransitionInfo(TransitionInfo transitionInfo) {
        return transitioninfoRepository.save(transitionInfo);
    }

    @Override
    public void deleteTransitionInfo(TransitionInfo transitionInfo) {
        transitioninfoRepository.delete(transitionInfo);
    }

}
