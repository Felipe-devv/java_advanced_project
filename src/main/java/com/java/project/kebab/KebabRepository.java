package com.java.project.kebab;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.project.kebab.domain.Kebab;

@Repository
public interface KebabRepository extends JpaRepository<Kebab,Integer>{

    List<Kebab> findByIsActiveTrue();
    
}
