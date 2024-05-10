import java.awt.*;
import java.awt.image.*;

public class BossTentacleAttack implements Runnable{
    
    private Thread activeThread;
    private Boss boss;

    private float firerate;
    private int numTentacles;
    private float mainAngle;
    private float rotSpeed;
    private float projectileSpeed;
    private float angularDispBeforeSwitch;
    private boolean dirCCW;
    private static final String tentPic = "Asterisk1.png";

    public BossTentacleAttack(Boss boss){
        this.boss = boss;
        firerate = 2;
        numTentacles = 6;
        mainAngle = 0;
        rotSpeed = .2f;
        projectileSpeed = 3;
        angularDispBeforeSwitch = (float)(Math.PI/2);
        dirCCW = true;
    }

    public void run(){
        try{
            while(activeThread != null){
                float tentAngleInc = (float)(2*Math.PI/numTentacles);
                for(int i=0;i<numTentacles;i++){
                    float angle = mainAngle+i*tentAngleInc;
                    Vector2 velocity = new Vector2(projectileSpeed, angle, true);
                    new BossProjectile(tentPic, boss.getPos(), .3f, velocity, true, true);
                }
                mainAngle += dirCCW ? rotSpeed : -rotSpeed;
                if(mainAngle >= angularDispBeforeSwitch || mainAngle < 0){
                    dirCCW = !dirCCW;
                }
                Thread.sleep((int)(1000/firerate));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void setActive(boolean active){
        if (active && activeThread == null) {
            activeThread = new Thread(this);
            activeThread.start();
        } else if (!active && activeThread != null) {
            activeThread = null;
        }
    }
}