package com.example.PurgeBatch.config;


import com.example.PurgeBatch.model.Person;
import com.example.PurgeBatch.readers.jdbcPagingReader.PersonPurgeReader;
import com.example.PurgeBatch.readers.queryProvider.PersonPurgeQueryProvider;
import com.example.PurgeBatch.writers.PersonPurgeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Autowired
    private DataSource datasource;


    @Autowired
    private PlatformTransactionManager transactionManager;


    @Autowired
    private JobRepository repository;

    @Bean
    public ItemWriter<Person> personItemWriter()
    {
        return new PersonPurgeWriter();
    }

    @Bean
    public PostgresPagingQueryProvider queryProvider()
    {
        return new PersonPurgeQueryProvider().queryProvider();
    }

    @Bean
    @StepScope
    public ItemReader<Person> personItemReader()
    {
        return new PersonPurgeReader().itemReader(datasource,queryProvider(),10);
    }

    @Bean
    public Step personStep()
    {
        return new StepBuilder("personStep",repository)
                .<Person,Person>chunk(10,transactionManager)
                .reader(personItemReader())
                .writer(personItemWriter())
                .build();
    }

    @Bean
    public Job personJob()
    {
        return new JobBuilder("personJob",repository)
                .incrementer(new RunIdIncrementer())
                .start(personStep())
                .build();
    }






}
