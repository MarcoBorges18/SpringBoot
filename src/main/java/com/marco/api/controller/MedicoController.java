package com.marco.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medico")
public class MedicoController{
    @PostMapping
    public  void cadastrar(@RequestBody String json){
        System.out.println(json);
    }
}