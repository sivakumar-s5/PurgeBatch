package com.example.PurgeBatch.readers.jdbcPagingReader;

import com.example.PurgeBatch.model.Person;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

public class PersonPurgeReader {

    public JdbcPagingItemReader<Person> itemReader(DataSource datasource, PostgresPagingQueryProvider queryProvider,Integer pageSize){
        return new JdbcPagingItemReaderBuilder<Person>()
                .name("personPurgeReader")
                .dataSource(datasource)
                .queryProvider(queryProvider)
                .rowMapper(new BeanPropertyRowMapper<>(Person.class))
                .build();
    }
}
