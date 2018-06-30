package com.homework.booking.repository;

import com.homework.booking.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Chae Chul Byung
 */
public interface EmpRepository extends JpaRepository<Emp, Long> {
}