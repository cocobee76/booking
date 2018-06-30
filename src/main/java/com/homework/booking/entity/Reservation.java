package com.homework.booking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created By Chae Chul Byung
 */
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
@Setter
@NoArgsConstructor
@IdClass(ReservationKey.class)
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer slotNo;

    @Id
    @Temporal(TemporalType.DATE)
    private Date date;

    @Id
    private Integer roomNo;

    @Column(nullable = false)
    private Integer empNo;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
}


