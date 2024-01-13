package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.MethodPay;

public interface MethodPayService {
    MethodPay findMethodPayById(Integer methodPayId);

    List<MethodPay> findAll();

    MethodPay saveMethodPay(MethodPay methodPay);

    MethodPay updateMethodPay(MethodPay methodPay);

    void deleteMethodPay(MethodPay methodPay);
}
