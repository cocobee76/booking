package com.homework.booking.repository;

import com.homework.booking.entity.Reservation;
import com.homework.booking.entity.ReservationKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created By Chae Chul Byung
 */
public interface ReservationRepository extends JpaRepository<Reservation, ReservationKey> {
    List<Reservation> findByDate(Date date);
}