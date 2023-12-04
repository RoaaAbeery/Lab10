package com.example.lab.Controller;

import com.example.lab.ApiResponse.Api;
import com.example.lab.Model.JobPost;
import com.example.lab.Model.User;
import com.example.lab.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/JobPost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;
    @GetMapping("/getJobPost")
    public ResponseEntity getJobPost(){
        return ResponseEntity.status(HttpStatus.OK).body(jobPostService.getJobPost());
    }
    @PostMapping("/addJobPost")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Job Post add"));
    }
    @PutMapping("/updateJobPost/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id,@RequestBody@Valid JobPost jobPost,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdate=jobPostService.updateJobPost(id, jobPost);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("Job Post updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("id not found"));
    }
    @DeleteMapping("/deleteJobPost/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){
        Boolean isdelete=jobPostService.deleteJobPost(id);
        if(isdelete){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("JobPost deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("id not found"));
    }
}
