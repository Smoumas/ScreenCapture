package com.screen;

import javazoom.jl.player.Player;
import sun.audio.AudioPlayer;

import javax.imageio.ImageIO;
import javax.naming.OperationNotSupportedException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
	// write your code here

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screen = new Rectangle(screenSize);
        Robot robot = null;
        try{
            robot = new Robot();
        }catch (AWTException e){
            e.printStackTrace();
        }
        File screenFile = new File("D:/test.png");
        boolean flag = true;
        if(robot == null){
            return;
        }
        BufferedImage captureBack = robot.createScreenCapture(screen);
        BufferedImage subImageBack = captureBack.getSubimage(1583, 1040, 40, 40);
        BufferedImage iconBack = null;
        try {
            iconBack = ImageIO.read(new File("D:/icon.png"));
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        while(flag) {
            BufferedImage capture = robot.createScreenCapture(screen);
            BufferedImage subImage = capture.getSubimage(1583, 1040, 40, 40);
//            try {
//                ImageIO.write(subImage, "png", screenFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            if(subImage.getRGB(20,20) != subImageBack.getRGB(20,20)){
                try {
                    flag = false;
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_W);
                    Thread.sleep(1000);
                    //(605.365) (648,404)
                    BufferedImage iconCapture = robot.createScreenCapture(screen);
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    boolean result = judge(iconBack,iconCapture);
                    if(result) {
                        Runtime.getRuntime().exec("wscript up.vbs");
                        File musicFile = new File("music.mp3");
                        FileInputStream fileInputStream = new FileInputStream(musicFile);
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                        Player player = new Player(bufferedInputStream);
                        player.play();
                        Runtime.getRuntime().exec("wscript down.vbs");
                    }else{
                        System.out.println(false);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean judge(BufferedImage iconBack,BufferedImage iconCapture){
        if(iconBack.getRGB(617,307) != iconCapture.getRGB(617,307)){
            return false;
        }
        if(iconBack.getRGB(617,334) != iconCapture.getRGB(617,334)){
            return false;
        }
        if(iconBack.getRGB(643,335) != iconCapture.getRGB(643,335)){
            return false;
        }
        if(iconBack.getRGB(630,325) != iconCapture.getRGB(630,325)){
            return false;
        }
        if(iconBack.getRGB(627,323) != iconCapture.getRGB(627,323)){
            return false;
        }
        if(iconBack.getRGB(643,306) != iconCapture.getRGB(643,306)){
            return false;
        }
        return true;
    }
}
