package com.homework.booking.service;

import com.homework.booking.dto.ReservationDto;
import com.homework.booking.entity.Reservation;
import com.homework.booking.entity.ReservationKey;
import com.homework.booking.exception.ReservationException;
import com.homework.booking.mapper.ReservationMapper;
import com.homework.booking.mapper.ReservationRepeater;
import com.homework.booking.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created By Chae Chul Byung
 */
@Service
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<Reservation> findAllReservation() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findOne(Date date, Integer slotNo, Integer roomNo) {
        ReservationKey key = new ReservationKey(date, slotNo, roomNo);
        return reservationRepository.findById(key);
    }

    @Transactional
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Transactional
    public void saveAll(ReservationDto dto) {

        List<Date> dates = ReservationRepeater.getDates(dto.getStartDate(), dto.getRepeatCount());

        dates.forEach(
                date -> {
                    ReservationKey key = new ReservationKey(date, dto.getSlotNo(), dto.getRoomNo());
                    if (reservationRepository.findById(key).isPresent()) {
                        throw new ReservationException(key.toString());
                    }
                }
        );

        reservationRepository.saveAll(reservationMapper.map(dto, dates));
    }
}
