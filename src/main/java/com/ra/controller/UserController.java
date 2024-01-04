package com.ra.controller;

import com.ra.exception.CustomException;
import com.ra.model.dao.request.UserRequest;
import com.ra.model.dao.response.UserResponse;
import com.ra.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public ResponseEntity<?>findAll(@RequestParam(name = "limit",defaultValue = "5") Integer limit,
                                    @RequestParam(name = "page",defaultValue = "0") Integer noPage){
        Pageable pageable= PageRequest.of(noPage,limit);
        Page<UserResponse>userResponses=userService.findAll(pageable);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }
    @PostMapping("user")
    public ResponseEntity<?>create(@RequestBody @Valid UserRequest userRequest) throws CustomException {
        UserResponse newUserResponse=userService.saveOrUpdate(userRequest);
        return new ResponseEntity<>(newUserResponse,HttpStatus.OK);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<?>findById(@PathVariable("id") Long id) throws CustomException{
        UserResponse userResponse=userService.findById(id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

}
