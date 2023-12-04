package com.example.lab.Controller;

import com.example.lab.ApiResponse.Api;
import com.example.lab.Model.JobApplication;
import com.example.lab.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/JobApp")
public class JobApplicationController {
    private final JobApplicationService applicationService;
    @GetMapping("/getApp")
    public ResponseEntity getApp(){
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getApp());
    }
    @PostMapping("/addApp")
    public ResponseEntity addApp(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
       Boolean applied= applicationService.addApp(jobApplication);
       if(applied){
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Job Applied"));
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Id not found"));
    }
    @DeleteMapping("/deleteApp/{id}")
    public ResponseEntity deleteApp (@PathVariable Integer id){
      Boolean isDelete=applicationService.deleteApp(id);
      if(isDelete){
          return ResponseEntity.status(HttpStatus.OK).body(new Api("Job Deleted"));
      }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Id not found"));

    }

}
