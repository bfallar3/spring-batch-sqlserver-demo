package com.benjsoft.springbatchsqlserverdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job updateTablesJob() {
        return new JobBuilder("updateTablesJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(updateTablesStep())
                .build();
    }

    @Bean
    public Step updateTablesStep() {
        return new StepBuilder("updateTablesStep", jobRepository)
                .tasklet(updateTablesTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet updateTablesTasklet() {
        return new UpdateTablesTasklet();
    }
}
