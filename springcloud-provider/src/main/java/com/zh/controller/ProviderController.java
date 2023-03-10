package com.zh.controller;

import com.zh.service.ProviderMqService;
import com.zh.util.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
@RequestMapping(("/provider"))
public class ProviderController {

    @Value("${message}")
    private String message;
    @Autowired
    ProviderMqService mqService;

    @GetMapping("/resource")
    public ResponseEntity provider() {
        return ResponseEntity.success(message);
    }

    @GetMapping("/send")
    public ResponseEntity providerMqMessage(String msg) {
        mqService.sendMessage(msg);
        return ResponseEntity.success();
    }
}
