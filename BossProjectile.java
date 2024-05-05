import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: is a projectile coming from the boss
 * to update: make width of collider line up with picture
 *              and if penetrates is false send to server, and send active state to server
 */

public class BossProjectile extends Projectile implements Runnable{
    private BufferedImage pic;
    private Vector2 size;
    private BoxCollider collider;
    private boolean penetrates, regenerates;
    private float regenerationTime;

    public BossProjectile(String pic, Vector2 position, float size, Vector2 velocity, boolean penetrates, boolean regenerates){
        super(position, velocity);
        this.size = new Vector2(size, size);
        this.penetrates = penetrates;
        this.regenerates = regenerates;

        regenerationTime = .5f;

        //get the x size
        collider = new BoxCollider(this, new Vector2(size, size), ColliderPurpose.ENEMYPROJECTILE);

        try{
            this.pic = ImageIO.read(new File(pic));
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getY()/2, size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.intX()), Screen.toPixels(size.intY()), null);
    }

    public void onCollisionEnter(Collider col){
        if(col.purpose() == ColliderPurpose.WALL){
            allProjectiles.remove(this);
        }else if(col.purpose() == ColliderPurpose.PLAYER){
            // automatically gets set to dud by the player
            if(collider.purpose() != ColliderPurpose.DUD){
                if (!penetrates) {
                    Collider.colliderList().remove(collider);
                    Projectile.allProjectiles.remove(this);
                } else if (regenerates) {
                    new Thread(this).start();
                }
            }
        }
    }

    public void run(){
        //regeneration timer
        try{
            Thread.sleep((int)(regenerationTime*1000));
        }catch(InterruptedException e){
            System.out.println(e);
        }

        collider.setPurpose(ColliderPurpose.ENEMYPROJECTILE);
    }
}
