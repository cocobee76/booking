package com.homework.booking.repository;

import com.homework.booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Chae Chul Byung
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
}