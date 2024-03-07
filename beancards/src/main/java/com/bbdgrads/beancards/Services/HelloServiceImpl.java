package com.bbdgrads.beancards.Services;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public String getHelloMessage() {
        return new  String("Hello from the HelloServiceImpl");
    }
    
}