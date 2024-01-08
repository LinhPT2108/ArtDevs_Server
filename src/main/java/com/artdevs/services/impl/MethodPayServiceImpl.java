package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.repositories.user.MethodpayRepository;
import com.artdevs.services.MethodPayService;

public class MethodPayServiceImpl implements MethodPayService {

    @Autowired
    MethodpayRepository methodpayRepository;

    @Override
    public MethodPay findMethodPayById(Integer methodPayId) {
        Optional<MethodPay> methodPayOptional = methodpayRepository.findById(methodPayId);
        return methodPayOptional.orElse(null);
    }

    @Override
    public List<MethodPay> findAll() {
        return methodpayRepository.findAll();
    }

    @Override
    public MethodPay saveMethodPay(MethodPay methodPay) {
        return methodpayRepository.save(methodPay);
    }

    @Override
    public MethodPay updateMethodPay(MethodPay methodPay) {
        return methodpayRepository.save(methodPay);
    }

    @Override
    public void deleteMethodPay(MethodPay methodPay) {
        methodpayRepository.delete(methodPay);
    }

}
