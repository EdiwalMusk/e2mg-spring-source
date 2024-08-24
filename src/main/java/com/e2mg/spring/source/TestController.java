package com.e2mg.spring.source;

import com.e2mg.test.service.TestService;
import com.e2mg.test.service.TestServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestServiceB testServiceB;

    @GetMapping("/method")
    public void method() {
        testService.print();
    }
}
