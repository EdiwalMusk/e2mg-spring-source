package com.e2mg.test;

import com.e2mg.test.service.TestService;
import com.e2mg.test.service.TestServiceB;
import org.springframework.context.annotation.Bean;

public class TestAutoConfiguration {
    @Bean
    public TestService testService() {
        return new TestService();
    }

    @Bean
    public TestServiceB testServiceB() {
        return new TestServiceB();
    }
}
