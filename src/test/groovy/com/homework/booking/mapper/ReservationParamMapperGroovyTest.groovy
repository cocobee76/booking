package com.homework.booking.mapper

import com.homework.booking.dto.ReservationParamDto
import spock.lang.Specification

import java.sql.Date
import java.text.SimpleDateFormat

/**
 * Created By Chae Chul Byung  
 */
class ReservationParamMapperGroovyTest extends Specification {

    def mapper = new ReservationParamMapper()

    static FORMAT = new SimpleDateFormat("yyyy-MM-dd")

    def "test map"() {

        given:
        def dto = new ReservationParamDto(
                slotNo: SLOTNO,
                roomNo: ROOMNO,
                empNo: EMPNO
        )
        when:
        def ret = mapper.map(dto, DATES)

        then:
        ret.size() == DATES.size()
        ret.get(0).getSlotNo() == dto.getSlotNo()
        ret.get(0).getRoomNo() == dto.getRoomNo()
        ret.get(0).getEmpNo() == dto.getEmpNo()

        where:
        DATES << [
                [new Date(FORMAT.parse("2018-07-02").getTime()), new Date(FORMAT.parse("2018-07-09").getTime())]
        ]
        SLOTNO << [1]
        ROOMNO << [2]
        EMPNO << [3]
    }
}
