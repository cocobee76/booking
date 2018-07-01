package com.homework.booking.service;

import com.homework.booking.dto.ReservationDailyDto;
import com.homework.booking.dto.ReservationParamDto;
import com.homework.booking.entity.Reservation;
import com.homework.booking.entity.ReservationKey;
import com.homework.booking.exception.ReservationException;
import com.homework.booking.mapper.ReservationMapper;
import com.homework.booking.mapper.ReservationParamMapper;
import com.homework.booking.mapper.ReservationRepeater;
import com.homework.booking.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created By Chae Chul Byung
 */
@Service
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationParamMapper paramMapper;

    private final ReservationMapper reservationMapper;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationParamMapper paramMapper, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.paramMapper = paramMapper;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationParamDto> findAllReservation() {
        return paramMapper.mapFrom(reservationRepository.findAll());
    }

    public Optional<Reservation> findOne(Date date, Integer slotNo, Integer roomNo) {
        ReservationKey key = new ReservationKey(date, slotNo, roomNo);
        return reservationRepository.findById(key);
    }

    public ReservationDailyDto findOccupied(String date) {
        Date sqlDate = Date.valueOf(date);
        List<Reservation> reservations = reservationRepository.findByDate(sqlDate);
        return reservationMapper.map(reservations, sqlDate);
    }


    @Transactional
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Transactional
    public void saveAll(ReservationParamDto dto) {

        List<Date> dates = ReservationRepeater.getDates(dto.getStartDate(), dto.getRepeatCount());

        dates.forEach(
                date -> {
                    ReservationKey key = new ReservationKey(date, dto.getSlotNo(), dto.getRoomNo());
                    if (reservationRepository.findById(key).isPresent()) {
                        throw new ReservationException(String.format("중복된 예약이 있습니다. slotNo: %s, roomNo: %s", dto.getSlotNo(), dto.getRoomNo()));
                    }
                }
        );

        reservationRepository.saveAll(paramMapper.map(dto, dates));
    }
}
