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
    private CircleCollider collider;
    private float lifeTime;
    private String[] pictureFiles = new String[] {
        "ParenthesesBL.png",
        "ParenthesesB.png",
        "ParenthesesBR.png",
        "ParenthesesR.png",
        "ParenthesesTR.png",
        "ParenthesesT.png",
        "ParenthesesTL.png",
        "ParenthesesL.png",
    };

    public PlayerProjectile(Vector2 position, float size, Vector2 velocity, float maxDist){
        super(position, velocity);
        this.size = new Vector2(size, size);

        //get the x size
        collider = new CircleCollider(this, size/4, ColliderPurpose.PLAYER_PROJECTILE);

        lifeTime = maxDist/velocity.magnitude();

        float angle = (float)Math.atan2(velocity.getY(), velocity.getX());
        float increment = (float)Math.PI/4;
        float start = -(float)Math.PI*7/8;
        try{
            pic = null;
            for(int i=0;i<pictureFiles.length-1;i++){
                if(angle > start+i*increment && angle < start+(i+1)*increment){
                    pic = ImageIO.read(new File(pictureFiles[i]));
                }
            }
            if(pic == null){
                pic = ImageIO.read(new File(pictureFiles[7]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static synchronized PlayerProjectile createProjectile(Vector2 position, float size, Vector2 velocity, float maxDist){
        return new PlayerProjectile(position, size, velocity, maxDist);
    }

    public void update(float deltaTime){
        super.update(deltaTime);
        lifeTime -= deltaTime;
        if(lifeTime <= 0){
            destroySelf();
        }
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getX()/2, size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }
    public Collider collider(){
        return collider;
    }

    public void onCollisionEnter(Collider col){
        if(col.purpose() == ColliderPurpose.WALL){
            destroySelf();
        }
    }
}
