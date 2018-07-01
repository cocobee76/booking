package com.homework.booking.mapper;

import com.homework.booking.dto.ReservationParamDto;
import com.homework.booking.entity.Reservation;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created By Chae Chul Byung
 */
@Component
public class ReservationParamMapper {

    public List<Reservation> map(ReservationParamDto dto, List<Date> dates) {

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

    public List<ReservationParamDto> mapFrom(List<Reservation> reservations) {

        return reservations.stream()
                .map(reservation -> {
                    ReservationParamDto dto = new ReservationParamDto();
                    dto.setStartDate(new Date(reservation.getDate().getTime()));
                    dto.setSlotNo(reservation.getSlotNo());
                    dto.setRoomNo(reservation.getEmpNo());
                    dto.setEmpNo(reservation.getEmpNo());
                    return dto;
                }).collect(Collectors.toList());
    }
}
