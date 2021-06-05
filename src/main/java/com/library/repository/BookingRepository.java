package com.library.repository;

import com.library.entity.Book;
import com.library.entity.Booking;
import com.library.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Booking findByMemberAndBook(Member member, Book book);
}
