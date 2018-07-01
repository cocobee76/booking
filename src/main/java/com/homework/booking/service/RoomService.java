package com.homework.booking.service;

import com.homework.booking.dto.RoomDto;
import com.homework.booking.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created By Chae Chul Byung
 */
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> getAllRoomDtos(){
        return roomRepository.findAll().stream()
                .map(emp -> mapper.map(emp, RoomDto.class))
                .collect(toList());
    }
}
