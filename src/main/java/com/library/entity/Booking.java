package com.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Table(name = "booking")
@Entity
@Data
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_Id",insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @Column(name = "borrowed_Date")
    private Instant borrowedDate;

    @Column(name = "return_Date")
    private Instant returnDate;

    @Column(name = "due_Date")
    private Instant dueDate;

    @Column(name = "status")
    private String status;

    @Column(name = "charges")
    private String charges;
}
