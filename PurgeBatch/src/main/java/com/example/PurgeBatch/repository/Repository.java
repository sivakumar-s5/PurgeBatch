package com.example.PurgeBatch.repository;

import com.example.PurgeBatch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Person,Integer> {
}
