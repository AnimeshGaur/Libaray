package com.library.controller;

import com.library.entity.Request;
import com.library.services.BookingService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping(value = "/return",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFine(@RequestBody Request request) throws NotFoundException {
        return ResponseEntity.ok(bookingService.checkFine(Long.valueOf(request.getMemberId()),Long.valueOf(request.getBookId())));
    }
}
