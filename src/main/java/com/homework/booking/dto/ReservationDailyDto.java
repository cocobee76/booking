package com.homework.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

/**
 * Created By Chae Chul Byung
 */
@Getter
@Builder
public class ReservationDailyDto {

    private Date date;
    private List<ReservationDto> reservations;

}
