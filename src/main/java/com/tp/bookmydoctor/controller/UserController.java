package com.tp.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.bookmydoctor.requestdto.UserRequestDto;
import com.tp.bookmydoctor.responsedto.GlobalResponseDto;
import com.tp.bookmydoctor.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public GlobalResponseDto registerUser(@RequestBody @Valid UserRequestDto requestDto){
        return new GlobalResponseDto(false,"user added successfully",userService.registerUser(requestDto));
    }
    
    @PostMapping("/loginuser")
    public GlobalResponseDto loginUser(@RequestBody@Valid UserRequestDto dto) {
    	return new GlobalResponseDto(false, "login ", userService.login(dto));
    }
    @GetMapping("/getalldoc")
    public GlobalResponseDto getAllDoctor(@RequestParam int pageno,@RequestParam int pagesize,@RequestParam String fieldName,@RequestParam String sortDirection) {
    	return new GlobalResponseDto(false, "List of Doctor :", userService.getAllDoc(pageno,pagesize,fieldName,sortDirection));
    }
    @GetMapping("/findby")
    public GlobalResponseDto findByNameAndSpec(@RequestParam String name) {
    	return new GlobalResponseDto(true, "List Of Doctor", userService.findBy(name));
    }
    @GetMapping("getallbook")
    public GlobalResponseDto getAllAppointment(@RequestBody @Valid  UserRequestDto dto ) {
    	return new GlobalResponseDto(false, "All Booking", userService.getAllBooking(dto));
    }
    
   
    }
