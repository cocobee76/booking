package com.homework.booking.controller;

import com.homework.booking.dto.ReservationDto;
import com.homework.booking.entity.Reservation;
import com.homework.booking.mapper.ReservationMapper;
import com.homework.booking.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created By Chae Chul Byung
 */
@RestController
@Slf4j
@RequestMapping("/")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Reservation> findAll() {
        return reservationService.findAllReservation();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String crate() {

        ReservationDto dto = new ReservationDto();
        dto.setStartDate(new Date());
        dto.setRoomNo(10);
        dto.setEmpNo(111);
        dto.setSlotNo(9);
        dto.setRepeatCount(10);

        reservationService.saveAll(dto);

        return "DONE";
    }
}
