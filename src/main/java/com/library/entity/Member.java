package com.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "member")
@Entity
@Data
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_Name")
    private String memberName;

    @Column(name = "book_Borrowed_Count")
    private Long bookBorrowedCount;

    @Column(name = "email")
    private String email;

    @Column(name = "isAdmin")
    private Boolean isAdmin = Boolean.FALSE;

    @OneToMany(mappedBy = "member",cascade=CascadeType.ALL)
    private Set<Booking> bookingList= new HashSet<>();
}
