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
    private static BufferedImage[] pictures;

    private boolean test = false;

    public PlayerProjectile(Vector2 position, float size, Vector2 velocity, float maxDist){
        super(position, velocity);
        this.size = new Vector2(size, size);
        test = true;

        //get the x size
        collider = new CircleCollider(this, size/4, ColliderPurpose.PLAYER_PROJECTILE);

        lifeTime = maxDist/velocity.magnitude();

        float angle = (float)Math.atan2(velocity.getY(), velocity.getX());
        float increment = (float)Math.PI/4;
        float start = -(float)Math.PI*7/8;
        pic = null;
        for(int i=0;i<pictures.length-1;i++){
            if(angle > start+i*increment && angle < start+(i+1)*increment){
                pic = pictures[i];
            }
        }
        if(pic == null){
            pic = pictures[7];
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
        if(!test){
            System.out.println("drawMe called before size was instantiated");
        }
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

    public static void setUpSprites(){
        try{
            pictures = new BufferedImage[] {
                ImageIO.read(new File("ParenthesesBL.png")),
                ImageIO.read(new File("ParenthesesB.png")),
                ImageIO.read(new File("ParenthesesBR.png")),
                ImageIO.read(new File("ParenthesesR.png")),
                ImageIO.read(new File("ParenthesesTR.png")),
                ImageIO.read(new File("ParenthesesT.png")),
                ImageIO.read(new File("ParenthesesTL.png")),
                ImageIO.read(new File("ParenthesesL.png")),
            };
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
