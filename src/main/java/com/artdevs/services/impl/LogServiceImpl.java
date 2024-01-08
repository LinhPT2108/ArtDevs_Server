package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.Log;
import com.artdevs.repositories.user.LogRepository;
import com.artdevs.services.LogService;

public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public Log findLogById(Integer logId) {
        Optional<Log> logOptional = logRepository.findById(logId);
        return logOptional.orElse(null);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log updateLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public void deleteLog(Log log) {
        logRepository.delete(log);
    }
}
