package com.zh.feign;

import com.zh.feign.fallback.ProviderFeignFallback;
import com.zh.util.response.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider", path = "/provider", fallbackFactory = ProviderFeignFallback.class)
@Component
public interface ProviderFeignService {

    @GetMapping("/resource")
    ResponseEntity provider();

    @GetMapping("/resource")
    ResponseEntity provider1();
}
