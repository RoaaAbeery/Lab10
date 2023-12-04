package com.example.lab.Service;

import com.example.lab.Model.JobApplication;
import com.example.lab.Model.JobPost;
import com.example.lab.Repository.JobApplicationRepository;
import com.example.lab.Repository.JobPostRepository;
import com.example.lab.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostService jobPostService;
    private final UserService userService;

    public List<JobApplication> getApp() {
        return jobApplicationRepository.findAll();
    }

    public Boolean addApp(JobApplication jobApplication) {
        for (int i = 0; i < jobPostService.getJobPost().size(); i++) {
            if (jobPostService.getJobPost().get(i).getId().equals(jobApplication.getJobPostId())) {
                for (int j = 0; j < userService.getusers().size(); j++) {
                    if (userService.getusers().get(j).getId().equals(jobApplication.getUserId())) {
                        jobApplicationRepository.save(jobApplication);
                        return true;
                    }

                }return false;
            }
        }return false;

    }
    public Boolean deleteApp(Integer id){
        JobApplication oldApp=jobApplicationRepository.getById(id);
        if(oldApp==null){
            return false;
        }
        jobApplicationRepository.delete(oldApp);
        return true;
    }

}