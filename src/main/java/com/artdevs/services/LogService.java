package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.Log;

public interface LogService {
    Log findLogById(Integer logId);

    List<Log> findAll();

    Log saveLog(Log log);

    Log updateLog(Log log);

    void deleteLog(Log log);
}
