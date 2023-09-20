package com.liu.webMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebMVCApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMVCApplication.class, args);
        System.out.println("WebMVC启动成功");
    }
}