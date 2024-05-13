import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: walking animation
 */

public class PlayerMovementAnimation implements Runnable{
    
    private boolean moving;
    private Player player;
    private BufferedImage[] sprites;
    private Thread curThread;
    private float timer;
    private final float animtionChangeTime;

    public PlayerMovementAnimation(Player player){
        this.player = player;
        moving = false;
        
        animtionChangeTime = .5f;
        timer = animtionChangeTime;

        sprites = new BufferedImage[2];
    }

    public void run(){
        try{
            PlayerAppearanceManager aM = player.appearanceManager;
            while(curThread != null){
                if(timer <= 0){
                    aM.setSprite(aM.sprite() == sprites[0] ? sprites[1] : sprites[0]);
                    timer = animtionChangeTime;
                }
                Thread.sleep(10);
                timer -= .01;
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void moving(boolean moving){
        if(!this.moving && moving){
            curThread = new Thread(this);
            curThread.start();
        }else if(this.moving && !moving){
            player.appearanceManager.setSprite(sprites[0]);
            curThread = null;
        }
        this.moving = moving;
    }

    public void updateColor(){
        try {
            String color = "";
            switch (player.color()) {
                case BLUE:
                    color = "Blue";
                    break;
                case GREEN:
                    color = "Green";
                    break;
                case YELLOW:
                    color = "Yellow";
                    break;
                case RED:
                    color = "Red";
                    break;
            }
            sprites[0] = ImageIO.read(new File(String.format("%s1.png", color)));
            sprites[1] = ImageIO.read(new File(String.format("%s2.png", color)));

            player.appearanceManager.setSprite(sprites[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
