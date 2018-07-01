package com.homework.booking.controller;

import com.homework.booking.dto.EmpDto;
import com.homework.booking.dto.ReservationDailyDto;
import com.homework.booking.dto.ReservationParamDto;
import com.homework.booking.dto.RoomDto;
import com.homework.booking.service.EmpService;
import com.homework.booking.service.ReservationService;
import com.homework.booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Chae Chul Byung
 */
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    private final RoomService roomService;

    private final EmpService empService;

    @Autowired
    public ReservationController(ReservationService reservationService, RoomService roomService, EmpService empService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.empService = empService;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "reservation";
    }

    @RequestMapping(value = "/occupied", method = RequestMethod.GET)
    @ResponseBody
    public ReservationDailyDto findOccupied(
            @RequestParam(value = "date", required = false) String date
    ) {
        return reservationService.findOccupied(date);
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public List<EmpDto> findAllEmps() {
        return empService.getAllEmpDtos();
    }

    @RequestMapping(value = "/room", method = RequestMethod.GET)
    @ResponseBody
    public List<RoomDto> findAllRooms() {
        return roomService.getAllRoomDtos();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String crate(@ModelAttribute ReservationParamDto reservationParamDto) {
        reservationService.saveAll(reservationParamDto);
        return "DONE";
    }
}