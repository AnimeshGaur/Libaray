package com.library.services;

import com.library.entity.*;
import com.library.repository.BookRepository;
import com.library.repository.BookingRepository;
import com.library.repository.MemberRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;


    /**
     * check fine
     *
     * @param memberId memberId
     * @param uuid uuid
     * @return {@link Response}
     * @see Response
     * @throws NotFoundException javassist. not found exception
     */
    public Response checkFine(Long memberId, Long uuid) throws NotFoundException {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("member not found"));
        Book book = bookRepository.findById(uuid).orElseThrow(() -> new NotFoundException("member not found"));
        returnBook(member,book);
        Booking booking= bookingRepository.findByMemberAndBook(member,book);
        if(booking !=null){
            if(booking.getReturnDate().isAfter(booking.getDueDate())){
                    Long fine = 0l;
                    long dates = booking.getDueDate().until(booking.getReturnDate(),ChronoUnit.DAYS);
                    if(dates == 0l){
                        return new Response("no fees charged");
                    }else if(dates <= 3l){
                        fine = dates*20;
                        booking.setCharges(Math.toIntExact(fine));
                        bookingRepository.saveAndFlush(booking);
                        return new Response("your total fine is :" + dates*20);
                    }else{
                        fine = dates*50;
                        booking.setCharges(Math.toIntExact(fine));
                        bookingRepository.saveAndFlush(booking);
                        return new Response("your total fine is :" + dates*50);
                    }

            }
        }else{
            throw new NotFoundException("Book can be returned by the borrowed member itself ");
        }
        return new Response("no fees charged");
    }

    private void returnBook(Member member, Book book){
        Booking booking= bookingRepository.findByMemberAndBook(member,book);
        booking.setStatus(Status.AVAILBALE.toString());
        if(booking.getDueDate().isAfter(Instant.now())){
            booking.setStatus(Status.OVERDUE.toString());
        }
        booking.setReturnDate(Instant.now());
        bookingRepository.saveAndFlush(booking);

        book.setStatus(Status.AVAILBALE.toString());
        book.setCount(book.getCount()+1);
        bookRepository.saveAndFlush(book);

        if (member.getBookBorrowedCount()<3){
            member.setBookBorrowedCount(member.getBookBorrowedCount()+1);
        }else{
            member.setBookBorrowedCount(3l);
        }

        memberRepository.saveAndFlush(member);

    }


}
