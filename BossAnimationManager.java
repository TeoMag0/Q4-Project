import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BossAnimationManager extends Startable implements Runnable{

    private Boss boss;
    private BufferedImage[] bossDown, bossUp;
    private Thread activeThread;
    
    public BossAnimationManager(Boss boss){
        this.boss = boss;

        try{
            bossDown = new BufferedImage[] {ImageIO.read(new File("Boss1.png")), ImageIO.read(new File("Boss1v2.png"))};
            bossUp = new BufferedImage[] {ImageIO.read(new File("Boss2.png")), ImageIO.read(new File("Boss2v2.png"))};
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void start(){
        activeThread = new Thread(this);
        activeThread.start();
    }

    public void run(){
        try{
            while (activeThread == Thread.currentThread()) {
                if (boss.appearanceManager.sprite() == bossDown[0]) {
                    boss.appearanceManager.setSprite(bossDown[1]);
                } else if (boss.appearanceManager.sprite() == bossDown[1]) {
                    boss.appearanceManager.setSprite(bossDown[0]);
                } else if (boss.appearanceManager.sprite() == bossUp[0]) {

                }
                Thread.sleep(500);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void phase2(){
        boss.appearanceManager.setSprite(bossDown[0]);
    }
    public void armsUp(){
        boss.appearanceManager.setSprite(bossUp[1]);
        activeThread = new Thread(this);
        activeThread.start();
    }
}
