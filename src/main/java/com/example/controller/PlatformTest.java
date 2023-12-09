package com.example.controller;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlatformTest
{
    @BeforeClass
    public static void initJFX() {
        new JFXPanel();
    }

    @Test
    public void getPlayernetdistanceTest()
    {
        double result = PlatformHandler.getPlayernetdistance();
        assertEquals(250, result, 0.001);
    }
}
