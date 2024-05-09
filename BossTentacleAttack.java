import java.awt.*;
import java.awt.image.*;

public class BossTentacleAttack extends Startable implements Runnable{
    
    private boolean active = false;
    private Boss boss;

    private float firerate;
    private int numTentacles;
    private float mainAngle;
    private float rotSpeed;
    private float projectileSpeed;
    private static final String tentPic = "Asterisk1.png";

    public BossTentacleAttack(Boss boss){
        this.boss = boss;
        firerate = 2;
        numTentacles = 6;
        mainAngle = 0;
        rotSpeed = .2f;
        projectileSpeed = 3;
    }
    public void start(){
        new Thread(this).start();
    }

    public void run(){
        try{
            while(true){
                if(active){
                    float tentAngleInc = (float)(2*Math.PI/numTentacles);
                    for(int i=0;i<numTentacles;i++){
                        float angle = mainAngle+i*tentAngleInc;
                        Vector2 velocity = new Vector2(projectileSpeed, angle, true);
                        new BossProjectile(tentPic, boss.getPos(), .3f, velocity, true, true);
                    }
                    mainAngle += rotSpeed;
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
