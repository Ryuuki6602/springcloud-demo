package com.example.nacosconsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class consumerController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/remote/{param}")
    public String rpc(@PathVariable String param){
        String msg = restTemplate.getForObject("http://nacos-provider/provider/test",String.class);
        return param + ", " + msg;
    }
}
