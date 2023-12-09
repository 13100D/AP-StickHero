package com.example.controller;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

class CherryTest {
    @Test
    void getNumCherriesTest() {
        Cherry.setNumCherries(5);
        assertEquals(5, Cherry.getNumCherries());
    }
    @Test
    void reviveSubtractCherriesTest()
    {
        Cherry.setNumCherries(10); // Set a number less than 20 for testing
        assertThrows(InsufficientCherriesException.class, Cherry::reviveSubtractCherries);
    }
}
