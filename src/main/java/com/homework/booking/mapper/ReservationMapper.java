package com.homework.booking.mapper;

import com.homework.booking.dto.ReservationDto;
import com.homework.booking.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created By Chae Chul Byung
 */
@Component
public class ReservationMapper {

    public List<Reservation> map(ReservationDto dto, List<Date> dates) {

        return dates.stream()
                .map(date -> {
                    Reservation reservation = new Reservation();
                    reservation.setDate(date);
                    reservation.setSlotNo(dto.getSlotNo());
                    reservation.setRoomNo(dto.getRoomNo());
                    reservation.setEmpNo(dto.getEmpNo());
                    return reservation;
                }).collect(toList());
    }
}
