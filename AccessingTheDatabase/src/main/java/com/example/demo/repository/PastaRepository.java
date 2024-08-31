package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Pasta;

public interface PastaRepository extends CrudRepository<Pasta, String> {
    
}
    
