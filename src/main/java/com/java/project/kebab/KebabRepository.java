package com.java.project.kebab;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface KebabRepository extends JpaRepository<Kebab,Integer>{

    List<Kebab> findByIsActiveTrue();
    
}
