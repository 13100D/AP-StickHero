package com.example.controller;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @BeforeAll
    static void initJFX()
    {
        new JFXPanel();
    }
    @Test
    public void singletonTest()
    {
        Rectangle rect1 = new Rectangle();
        ImageView img1 = new ImageView();
        Player player1 = Player.getInstance(rect1, img1);
        Rectangle rect2 = new Rectangle();
        ImageView img2 = new ImageView();
        Player player2 = Player.getInstance(rect2, img2);
        assertEquals(player1, player2);
    }
}
