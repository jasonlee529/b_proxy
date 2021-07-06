package com.lee.tools.proxy.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BProxyApplication.class, args);
    }

    @RestController
    class EchoController {
        @RequestMapping("")
        public String echo() {
            return "Hello";
        }
    }
}
