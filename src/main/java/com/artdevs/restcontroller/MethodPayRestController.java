package com.artdevs.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.dto.transition.MethodPayDTO;
import com.artdevs.mapper.MethodPayMapper;
import com.artdevs.repositories.user.MethodpayRepository;
import com.artdevs.services.MethodPayService;
import com.artdevs.services.impl.transition.MethodPayServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class MethodPayRestController {
    @Autowired
    MethodPayServiceImpl methodPayServiceImpl;

    @PostMapping("/methodpay")
    public ResponseEntity<MethodPay> postMethodPay(@RequestBody MethodPayDTO methodPayDTO) {
        return ResponseEntity.ok(methodPayServiceImpl.saveMethodPay(MethodPayMapper.convertToMethodPay(methodPayDTO)));
    }

    @GetMapping("/methodpay")
    public ResponseEntity<List<MethodPay>> getMethodPay() {
        return ResponseEntity.ok(methodPayServiceImpl.findAll());
    }
}
