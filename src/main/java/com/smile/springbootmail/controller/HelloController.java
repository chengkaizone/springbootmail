package com.smile.springbootmail.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;

    @GetMapping("/hello")
    public void hello() {
        System.out.println("开始执行任务...");
        try {
            jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("任务执行出错...");
        }
        System.out.println("任务执行完成...");
    }

}
