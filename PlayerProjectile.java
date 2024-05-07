import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: player's projectile
 * to update: make width of collider line up with picture
 *              and if penetrates is false send to server, and send active state to server
 */

public class PlayerProjectile extends Projectile{
    private BufferedImage pic;
    private Vector2 size;
    private BoxCollider collider;

    public PlayerProjectile(String pic, Vector2 position, float size, Vector2 velocity){
        super(position, velocity);
        this.size = new Vector2(size, size);

        //get the x size
        collider = new BoxCollider(this, new Vector2(size, size), ColliderPurpose.PLAYER_PROJECTILE);

        try{
            this.pic = ImageIO.read(new File(pic));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getY()/2, size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }

    public void onCollisionEnter(Collider col){
        if(col.purpose() == ColliderPurpose.WALL){
            destroySelf();
        }
    }
    public void destroySelf(){
        allProjectiles.remove(this);
        Collider.colliderList().remove(collider);
    }
}
