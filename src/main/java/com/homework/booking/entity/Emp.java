package com.homework.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created By Chae Chul Byung
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {

    @Id
    private Long empNo;

    @Column(length = 20)
    private String name;
}
