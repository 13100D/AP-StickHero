package com.example.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Objects;


public class PauseScreenController extends ControllerBase{
    public ImageView volumeicon;
    public Text deadtax;
    public static Text deadtax_stat;
    public ImageView deadonly;
    public static ImageView deadonly_stat;
    public Button volumeButton;
    public Button switchButton;
    private final boolean isMuted = MainScreenController.isMuted();
    public Text pausetext;
    public static Text pausetext_stat;

    public void switchToGameScreen() {
        System.out.println("Starting Game");

        try {
            if (!Player.isAlive() && Cherry.getNumCherries() >= 20) {
                Cherry.setNumCherries(Cherry.getNumCherries() - 20);
//            PlatformHandler.Handlerreset();
                Scene abc = MainApp.getscenes().get(1);
//            abc = (new Scene(loadFXML("/GameScreen.fxml"), 1280, 720));
                ControllerBase.stage.setScene(abc);
//            GameScreenController.postInit();
                Player.fixposition();
                Player.writeNumCherriesToFile();
            }

            else if (Player.isAlive()) {
                ControllerBase.stage.setScene(MainApp.getscenes().get(1));
            }

            else
            {
                throw new InsufficientCherriesException("Not enough cherries to revive");
            }
        }

        catch (InsufficientCherriesException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initialize(){
        pausetext_stat = pausetext;
        deadtax_stat = deadtax;
        deadonly_stat = deadonly;
    }
    public static void postinit() {
        if(!Player.isAlive())
        {
            System.out.println("dead");
            pausetext_stat.setText("You Died!");
            //show dead screen

        }
        else
        {
            deadtax_stat.setOpacity(0);
            deadonly_stat.setOpacity(0);
            //show pause screen
        }
    }
    public void buyspr4(MouseEvent mouseEvent) {
        try {
            if (Cherry.getNumCherries() < 20) {
                throw new InsufficientCherriesException("Not enough cherries to buy sprite 4");
            }

            Cherry.setNumCherries(Cherry.getNumCherries() - 20);
            System.out.println("bought sprite 4");
            Player.setPlayerSprite("file:src/main/resources/dinonig3.png");
            Player.writeNumCherriesToFile();
        }

        catch (InsufficientCherriesException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyspr3(MouseEvent mouseEvent) {
        try {
            if (Cherry.getNumCherries() < 20) {
                throw new InsufficientCherriesException("Not enough cherries to buy sprite 3");
            }

            Cherry.setNumCherries(Cherry.getNumCherries() - 20);
            System.out.println("bought sprite 3");
            Player.setPlayerSprite("file:src/main/resources/dinonig2.png");
            Player.writeNumCherriesToFile();
        }

        catch (InsufficientCherriesException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyspr2(MouseEvent mouseEvent) {
        try {
            if (Cherry.getNumCherries() < 20) {
                throw new InsufficientCherriesException("Not enough cherries to buy sprite 2");
            }

            Cherry.setNumCherries(Cherry.getNumCherries() - 20);
            System.out.println("bought sprite 2");
            Player.setPlayerSprite("file:src/main/resources/dinonig.png");
            Player.writeNumCherriesToFile();
        }

        catch (InsufficientCherriesException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyspr1(MouseEvent mouseEvent)
    {
        try {
            if (Cherry.getNumCherries() < 20) {
                throw new InsufficientCherriesException("Not enough cherries to buy sprite 1");
            }

            Cherry.setNumCherries(Cherry.getNumCherries() - 20);
            System.out.println("bought sprite 1");
            Player.setPlayerSprite("file:src/main/resources/dinovanilla.png");
            Player.writeNumCherriesToFile();
        }

        catch (InsufficientCherriesException e) {
            System.out.println(e.getMessage());
        }
    }

    public void volumetoggle(MouseEvent mouseEvent) {
        MainScreenController.mute_stat();
        updateVolumeButtonImage();
        System.out.println("Volume is " + (MainScreenController.isMuted() ? "muted" : "unmuted"));

    }

    private void updateVolumeButtonImage() {
        String imageName = MainScreenController.isMuted() ? "/soundmutedicon.png" : "/soundyesicon.png";
        setImage(volumeicon, imageName);
    }

    private Image getImage(String imageName)
    {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
    }

    private void setImage(ImageView image, String imageName)
    {
        image.setImage(getImage(imageName));
    }

    //add boolean check in init to see if dead or just regular old paused
}
