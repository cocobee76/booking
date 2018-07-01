package com.homework.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * Created By Chae Chul Byung
 */
@Getter
@Setter
public class ReservationParamDto {
    private Date startDate;
    private Integer slotNo;
    private Integer roomNo;
    private Integer empNo;
    private Integer repeatCount;
}
