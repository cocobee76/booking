package com.homework.booking.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Chae Chul Byung
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationKey implements Serializable {

    private Date date;

    private Integer slotNo;

    private Integer roomNo;

}