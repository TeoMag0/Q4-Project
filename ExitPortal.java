import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ExitPortal implements DrawableObject, Transform, HasCollider{

    private Vector2 locationRC;
    private Vector2 position;
    private Vector2 size;
    private BufferedImage sprite;
    private BoxCollider collider;
    private static ExitPortal portal;
    private InteractionPrompt interactionPrompt;
    
    public ExitPortal(){
        size = new Vector2(1,1);
        locationRC = Vector2.zero();
        position = TileMap.Singleton.rcToCoords(locationRC);

        collider = new BoxCollider(this, size, ColliderPurpose.PORTAL);

        interactionPrompt = new InteractionPrompt(this, new Vector2(0, size.getY()/2+.2f));

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void onCollisionEnter(Collider col){
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(position, new Vector2(-size.getX()/2,size.getY()/2)));
        g.drawImage(sprite, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);

        if(playerTouching()){
            interactionPrompt.drawMe(g);
        }
    }
    public Vector2 getPos(){
        return position;
    }
    public Collider collider(){
        return collider;
    }

    public static void createPortal(){
        portal = new ExitPortal();
    }
    public static void deletePortal(){
        Collider.removeCollider(portal.collider());
        portal = null;
    }
    public static void drawPortal(Graphics g){
        if(portal != null){
            portal.drawMe(g);
        }
    }
    public static boolean playerTouching(){
        if(portal != null){
            return Collider.checkCollision(portal.collider(), Screen.player.collisionManager.collider());
        }
        return false;
    }

    public static void teleportPlayer(){
        Screen.player.setPos(new Vector2(-10, 0));
    }
}
