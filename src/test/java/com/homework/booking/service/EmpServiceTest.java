package com.homework.booking.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created By Chae Chul Byung
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class EmpServiceTest {

    @Autowired
    private EmpService empService;

    /*
        data.sql에서 insert한 사용자 관련 메타정보 확인
     */

    @Test
    public void testGetAllEmpDtos() {
        assertThat(empService.getAllEmpDtos().size(), CoreMatchers.is(3));
    }

}