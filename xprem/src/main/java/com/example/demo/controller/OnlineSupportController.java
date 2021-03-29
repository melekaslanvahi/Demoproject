package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.models.OnlineSupportRequest;
import com.example.demo.models.OnlineSupportResponse;
import com.example.demo.service.MemberService;
import com.example.demo.service.OnlineSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("onlineSuppports")
@CrossOrigin
@Validated
public class OnlineSupportController {

    @Autowired
    OnlineSupportService onlineSupportService;

    @Autowired
    public OnlineSupportController( OnlineSupportService onlineSupportService) {
        this.onlineSupportService = onlineSupportService;
    }

    /*
    @RequestMapping("/")
    public String index() {
        return "Merhaba Spring Boot!";
    }
     */

    /*
    @PostMapping
    public CustomerResponse addCustomer(
            @RequestBody  @Validated CustomerRequest customerRequest) {
        return customerService.add(customerRequest);
    }
    */

    @PostMapping(value = "addSupport")
    public ResponseEntity<OnlineSupportResponse> addOnlineSupport(@RequestBody @Validated OnlineSupportRequest onlineSupportRequest) {

        OnlineSupportResponse onlineSupportResponse = onlineSupportService.addOnlineSupport( onlineSupportRequest );
        return new ResponseEntity<>(onlineSupportResponse, HttpStatus.OK);
    }
}
