package com.homework.booking.mapper

import spock.lang.Specification
import spock.lang.Unroll

import java.text.SimpleDateFormat

/**
 * Created By Chae Chul Byung  
 */
class ReservationRepeaterGroovyTest extends Specification {

    static FORMAT = new SimpleDateFormat("yyyy-MM-dd")

    @Unroll
    def "test getDates"() {

        given:
        when:
        def res = ReservationRepeater.getDates(new java.sql.Date(FORMAT.parse(DATE).getTime()), 5)
        println(res)
        then:

        for (int i = 0; i < res.size(); i++) {
            FORMAT.format(res.get(i)) == RESULT.get(i)
        }

        where:
        DATE << ["2018-07-08", "2018-12-27"]
        RESULT << [
                ["2018-07-08", "2018-07-15", "2018-07-22", "2018-07-29", "2018-08-05"],
                ["2018-12-27", "2019-01-03", "2019-01-10", "2019-01-17", "2019-01-24"]
        ]

    }

}
