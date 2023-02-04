package com.zh.feign.fallback;

import com.zh.feign.ProviderFeignService;
import com.zh.util.response.ResponseEntity;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

@Service
public class ProviderFeignFallback implements FallbackFactory<ProviderFeignService> {

    @Override
    public ProviderFeignService create(Throwable cause) {
        return new ProviderFeignService() {
            @Override
            public ResponseEntity provider() {
                return ResponseEntity.fail("服务暂时不可用");
            }

            @Override
            public ResponseEntity provider1() {
                return ResponseEntity.fail("服务暂时不可用");
            }
        };
    }
}
