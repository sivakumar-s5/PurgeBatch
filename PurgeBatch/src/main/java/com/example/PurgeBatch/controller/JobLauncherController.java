package com.example.PurgeBatch.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/launch-job")
public class JobLauncherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job personJob;

    @GetMapping
    public String LaunchJob()
    {
        try{
            jobLauncher.run(personJob,new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters());
            return "Job Successfully started.";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Job Failed to start.";
        }
    }
}
