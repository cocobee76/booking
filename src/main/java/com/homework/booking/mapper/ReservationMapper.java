package com.homework.booking.mapper;

import com.homework.booking.dto.ReservationDailyDto;
import com.homework.booking.dto.ReservationDto;
import com.homework.booking.entity.Reservation;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created By Chae Chul Byung
 */
@Component
public class ReservationMapper {

    public ReservationDailyDto map(List<Reservation> reservations, Date date) {

        return ReservationDailyDto.builder()
                .date(date)
                .reservations(
                        reservations.stream()
                                .map(reservation -> ReservationDto.builder().slotNo(reservation.getSlotNo()).roomNo(reservation.getRoomNo()).empNo(reservation.getEmpNo()).build())
                                .collect(toList()))
                .build();
    }
}
