package com.imei.imei.controller;


import com.imei.imei.service.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home
{
    @GetMapping("/api/v2/imei")
    public String doValidation(@RequestBody String imeiNumber)
    {
        imeiNumber = imeiNumber.trim();
        try {
            return Validator.getImei(imeiNumber);
        }catch (Exception ae){
            return ae.getMessage();
        }
    }
}
