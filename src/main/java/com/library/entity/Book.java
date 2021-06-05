package com.library.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private Long uuid;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "author_Name")
    private String authorName;

    @NotNull
    @Column(name = "category")
    private String category;


    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "count")
    private Integer count;

    @Column(name = "return_Dates")
    private Integer returnDates;

    @OneToMany(mappedBy = "book",cascade=CascadeType.ALL)
    private List<Booking> bookingList= new ArrayList<>();


}
