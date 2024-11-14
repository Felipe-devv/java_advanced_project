package com.java.project.paragon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagonRepository extends JpaRepository<Paragon, Integer> {

}
