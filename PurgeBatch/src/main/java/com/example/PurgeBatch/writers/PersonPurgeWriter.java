package com.example.PurgeBatch.writers;

import com.example.PurgeBatch.model.Person;
import com.example.PurgeBatch.repository.Repository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonPurgeWriter implements ItemWriter<Person> {

    @Autowired
    public Repository repository;

    @Override
    public void write(Chunk<? extends Person> PersonList) throws Exception {
        for(Person p: PersonList)
        {
            if(p.getRole().equals("CEO"))
            {
                Integer id=p.getId();
                repository.deleteById(id);
            }
        }

    }
}
