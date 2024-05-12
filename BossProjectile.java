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
    private Collider collider;
    private boolean penetrates, regenerates;
    private float regenerationTime;
    private boolean wallImpervious;

    public BossProjectile(String pic, Vector2 position, float size, Vector2 velocity, boolean penetrates, boolean regenerates){
        super(position, velocity);
        this.size = new Vector2(size, size);
        this.penetrates = penetrates;
        this.regenerates = regenerates;

        regenerationTime = .5f;
        wallImpervious = false;

        //get the x size
        collider = new BoxCollider(this, new Vector2(size, size), ColliderPurpose.ENEMY_PROJECTILE);

        try{
            this.pic = ImageIO.read(new File(pic));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static synchronized BossProjectile createProjectile(String pic, Vector2 position, float size, Vector2 velocity, boolean penetrates, boolean regenerates){
        return new BossProjectile(pic, position, size, velocity, penetrates, regenerates);
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getX()/2, size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }
    public void wallImpervious(boolean impervious){
        wallImpervious = impervious;
    }

    public void onCollisionEnter(Collider col){
        if(col.purpose() == ColliderPurpose.WALL && !wallImpervious){
            destroySelf();
        }else if(col.purpose() == ColliderPurpose.PLAYER){
            // automatically gets set to dud by the player
            if(collider.purpose() != ColliderPurpose.DUD){
                if (!penetrates) {
                    destroySelf();
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
            e.printStackTrace();
        }

        collider.setPurpose(ColliderPurpose.ENEMY_PROJECTILE);
    }
    public Collider collider(){
        return collider;
    }
}
