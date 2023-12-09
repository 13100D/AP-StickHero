package com.example.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CherryTest {
    @Test
    void reviveSubtractCherries_insufficientCherriesException() {
        Cherry.setNumCherries(10);
        assertThrows(InsufficientCherriesException.class, Cherry::reviveSubtractCherries);
    }
}
