import java.awt.*;
import java.awt.image.*;

public class BossTentacleAttack extends Startable implements Runnable{
    
    private boolean active = false;
    private Boss boss;

    private float firerate;
    private static final String tentPic = "Asterisk1.png";

    public BossTentacleAttack(Boss boss){
        this.boss = boss;
        firerate = 2;
    }
    public void start(){
        new Thread(this).start();
    }

    public void run(){
        try{
            while(true){
                if(active){
                    Vector2 velocity = new Vector2(0, -1);
                    new BossProjectile(tentPic, boss.getPos(), .3f, velocity, true, true);
                    Thread.sleep((int)(1000/firerate));
                }
                Thread.sleep(10);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
