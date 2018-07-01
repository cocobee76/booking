package com.homework.booking.mapper

import com.homework.booking.entity.Reservation
import spock.lang.Specification
import spock.lang.Unroll

import java.sql.Date

/**
 * Created By Chae Chul Byung  
 */
class ReservationMapperGroovyTest extends Specification {

    def mapper = new ReservationMapper()

    @Unroll
    def "test map"() {

        given:
        def reservations = RESERVATIONS
        def date = DATE

        when:
        def dto = mapper.map(reservations, date)

        then:
        dto.getReservations().size() == RESULT

        where:
        RESERVATIONS << [
                [new Reservation(slotNo: 1, roomNo: 2, empNo: 3)],
                [new Reservation(slotNo: 1, roomNo: 2, empNo: 3), new Reservation(slotNo: 1, roomNo: 2, empNo: 3)],
                [new Reservation(slotNo: 1, roomNo: 2, empNo: 3), new Reservation(slotNo: 1, roomNo: 2, empNo: 3), new Reservation(slotNo: 1, roomNo: 2, empNo: 3)],
        ]
        DATE << [
                Date.valueOf("2018-07-01"),
                Date.valueOf("2018-07-01"),
                Date.valueOf("2018-07-01"),
        ]
        RESULT << [1, 2, 3]


    }

}
