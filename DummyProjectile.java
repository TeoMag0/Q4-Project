import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class DummyProjectile extends Projectile {

    private float lifetime;
    private Collider collider;
    private static BufferedImage[] pictures;
    private BufferedImage pic;
    private Vector2 size;
    
    public DummyProjectile(Vector2 pos, Vector2 velocity){
        super(pos, velocity);
        lifetime = Screen.player.attackManager.maxDist/velocity.magnitude();
        collider = new CircleCollider(this, .1f, ColliderPurpose.DUD);
        size = new Vector2(Screen.player.attackManager.projectileSize(), Screen.player.attackManager.projectileSize());

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

    @Override
    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getX()/2, size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        lifetime -= deltaTime;

        if(lifetime <= 0){
            destroySelf();
        }
    }
    @Override
    public Collider collider(){
        return collider;
    }

    @Override
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
