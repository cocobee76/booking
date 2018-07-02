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
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    /*
        data.sql에서 insert한 미팅룸 관련 메타정보 확인
     */

    @Test
    public void testGetAllRoomDtos() {
        assertThat(roomService.getAllRoomDtos().size(), CoreMatchers.is(7));
    }
}