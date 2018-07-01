package com.homework.booking.mapper;

import java.util.Calendar;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created By Chae Chul Byung
 */
public class ReservationRepeater {

    private static Date getNextWeekday(Date date, Integer week) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 7 * week);
        return new Date(cal.getTimeInMillis());
    }

    public static List<Date> getDates(Date startDate, Integer repeatCount) {

        if (repeatCount == null) {
            return Collections.singletonList(startDate);
        } else {
            return IntStream
                    .range(0, repeatCount)
                    .mapToObj(i -> getNextWeekday(startDate, i))
                    .collect(toList());
        }
    }
}
