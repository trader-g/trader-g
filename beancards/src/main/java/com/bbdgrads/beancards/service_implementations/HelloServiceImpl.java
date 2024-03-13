package com.bbdgrads.beancards.service_implementations;

import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.services.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String getHelloMessage() {
        return "Hello from the HelloServiceImpl";
    }

}