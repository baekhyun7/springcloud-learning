package com.zh.controller;

import com.zh.feign.ProviderFeignService;
import com.zh.util.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/consumer"))
public class ConsumerController {

    @Autowired
    ProviderFeignService providerFeignService;

    @GetMapping("/hello")
    public ResponseEntity sayHello() {
        return ResponseEntity.success("consumer say hello");
    }

    @GetMapping("/provider")
    public ResponseEntity consumerProvider() {
        return providerFeignService.provider();
    }
}
