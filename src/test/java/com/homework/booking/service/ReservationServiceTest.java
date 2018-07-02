package com.homework.booking.service;

import com.homework.booking.dto.ReservationDailyDto;
import com.homework.booking.dto.ReservationParamDto;
import com.homework.booking.exception.ReservationException;
import com.homework.booking.mapper.ReservationRepeater;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created By Chae Chul Byung
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testFindOccupied() {
        ReservationDailyDto occupied = reservationService.findOccupied("2018-07-01");
        assertThat(occupied.getReservations().size(), CoreMatchers.is(1));
    }

    @Test
    public void testSaveAndRetrieve() throws Exception {

        Date startDate = new Date(FORMAT.parse("2018-07-02").getTime());
        Integer empNo = 1;
        Integer roomNo = 2;
        Integer slotNo = 10;
        Integer repeatCount = 15;

        ReservationParamDto dto = new ReservationParamDto();
        dto.setStartDate(startDate);
        dto.setEmpNo(empNo);
        dto.setRoomNo(roomNo);
        dto.setSlotNo(slotNo);
        dto.setRepeatCount(repeatCount);

        reservationService.saveAll(dto);

        List<Date> dates = ReservationRepeater.getDates(startDate, repeatCount);

        dates.forEach(date -> assertTrue(reservationService.findOne(date, slotNo, roomNo).isPresent()));
    }
}