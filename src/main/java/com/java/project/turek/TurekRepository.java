package com.java.project.turek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurekRepository extends JpaRepository<Turek, Integer> {
List<Turek> findByStatusNot(int status);
}

