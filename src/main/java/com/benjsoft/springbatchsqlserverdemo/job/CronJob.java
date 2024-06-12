package com.benjsoft.springbatchsqlserverdemo.job;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJob {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job updateTablesJob;

    @Scheduled(cron = "*/10 * * * * *")
    public void cronJob1() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("jobId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(updateTablesJob, jobParameters);
    }
}
