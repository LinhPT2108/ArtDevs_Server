package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.TransitionInfo;

public interface TransitionInfoService {
    TransitionInfo findTransitionInfoById(String transitionInfoId);

    List<TransitionInfo> findAll();

    TransitionInfo saveTransitionInfo(TransitionInfo transitionInfo);

    TransitionInfo updateTransitionInfo(TransitionInfo transitionInfo);

    void deleteTransitionInfo(TransitionInfo transitionInfo);
}
