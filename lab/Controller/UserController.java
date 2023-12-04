package com.example.lab.Controller;

import com.example.lab.ApiResponse.Api;
import com.example.lab.Model.User;
import com.example.lab.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/getUser")
    public ResponseEntity getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getusers());
    }
    @PostMapping("/adduser")
    public ResponseEntity adduser(@RequestBody@Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("user add"));
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody@Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdate=userService.updateUser(id, user);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("User updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("id not found"));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        Boolean isdelete=userService.deleteUser(id);
        if(isdelete){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("User deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("id not found"));
    }
}
