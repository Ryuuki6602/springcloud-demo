package com.example.nacosprovider.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class providerController {



    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/test")
    public String test() {
        return "Here is naocs-provider! RPC has been called successfully! current port: " + serverPort;
    }
}
