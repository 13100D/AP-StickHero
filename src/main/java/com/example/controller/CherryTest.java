package com.example.controller;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CherryTest {
    @BeforeClass
    public static void initJFX() {
        new JFXPanel();
    }

    @Test
    public void getNumCherriesTest() {
        Cherry.setNumCherries(5);
        assertEquals(5, Cherry.getNumCherries());
    }

    @Test
    public void reviveSubtractCherriesTest_Insufficient() {
        Cherry.setNumCherries(5);
        assertThrows(InsufficientCherriesException.class, Cherry::reviveSubtractCherries);
    }

    @Test
    public void reviveSubtractCherriesTest_Sufficient() throws InsufficientCherriesException {
        Cherry.setNumCherries(30);
        Cherry.reviveSubtractCherries();
        assertEquals(10, Cherry.getNumCherries());
    }

    @Test
    public void isCherrySpawnedTest() {
        Cherry.setCherrySpawned(true);
        assertTrue(Cherry.isCherrySpawned());
    }
}
