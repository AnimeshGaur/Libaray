package com.library.services;

import com.library.entity.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest extends BookingService {

    @BeforeEach
    void setUp() {
    }



    @SneakyThrows
    @Test
    void testCheckFine() {
        Response response = checkFine(1l,1l);
    }
}