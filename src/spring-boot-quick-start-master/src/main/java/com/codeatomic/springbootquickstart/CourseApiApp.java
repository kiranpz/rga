package com.codeatomic.springbootquickstart;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class CourseApiApp implements CommandLineRunner
{
	
    @Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    Job job;

	public static void main(String[] args) {
		SpringApplication.run(CourseApiApp.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception 
	{
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
		
	}

}
