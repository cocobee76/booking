package com.homework.booking.controller;

import com.homework.booking.dto.ReservationDailyDto;
import com.homework.booking.service.ReservationService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created By Chae Chul Byung
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ReservationControllerTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    public void testDataRoutingWebSocketToHttp() {
        ReservationDailyDto occupied = reservationService.findOccupied("2018-07-01");
        assertThat(occupied.getReservations().size(), CoreMatchers.is(1));

    }
}