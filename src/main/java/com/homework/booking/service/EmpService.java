package com.homework.booking.service;

import com.homework.booking.dto.EmpDto;
import com.homework.booking.repository.EmpRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created By Chae Chul Byung
 */
@Service
public class EmpService {

    private final EmpRepository empRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public EmpService(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    public List<EmpDto> getAllEmpDtos() {
        return empRepository.findAll().stream()
                .map(emp -> mapper.map(emp, EmpDto.class))
                .collect(toList());
    }
}
