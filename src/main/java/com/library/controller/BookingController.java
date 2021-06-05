package com.library.controller;

import com.library.services.BookingService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/return/{memberId}/{uuid}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFine(@PathVariable("memberId") String memberId, @PathVariable("uuid") String uuid) throws NotFoundException {
        return ResponseEntity.ok(bookingService.checkFine(Long.valueOf(memberId),Long.valueOf(uuid)));
    }
}
