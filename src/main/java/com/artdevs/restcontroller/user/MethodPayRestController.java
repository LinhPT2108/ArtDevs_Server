package com.artdevs.restcontroller.user;

import java.util.ArrayList;
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
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class MethodPayRestController {
    @Autowired
    MethodPayService methodPayService;

    @Autowired
    MethodpayRepository methodpayRepository;

    @PostMapping("/methodpay")
    public ResponseEntity<MethodPay> postMethodPay(@RequestBody MethodPayDTO methodPayDTO) {
        return ResponseEntity.ok(methodPayService.saveMethodPay(MethodPayMapper.convertToMethodPay(methodPayDTO)));
    }

    @GetMapping("/methodpay")
    public ResponseEntity<List<MethodPayDTO>> getMethodPay() {
        List<MethodPayDTO> listMethodPayDTO = new ArrayList<>();
        List<MethodPay> listMethodPay = methodpayRepository.findAll();
        for (MethodPay methodPay : listMethodPay) {
            listMethodPayDTO.add(MethodPayMapper.convertToMethodPayDTO(methodPay));
        }

        return ResponseEntity.ok(listMethodPayDTO);
    }
}
