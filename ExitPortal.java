import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ExitPortal extends InteractableObject{

    private Vector2 locationRC;
    private Vector2 position;
    private Vector2 size;
    private BufferedImage sprite;
    private BoxCollider collider;
    private static ExitPortal portal;
    private InteractionPrompt interactionPrompt;
    
    public ExitPortal(){
        super();
        size = new Vector2(1,1);
        locationRC = Vector2.zero();
        position = TileMap.Singleton.rcToCoords(locationRC);

        collider = new BoxCollider(this, size, ColliderPurpose.INTERACTABLE);

        interactionPrompt = new InteractionPrompt(this, new Vector2(0, size.getY()/2+.2f));

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void interacted(){
        Screen.player.setPos(new Vector2(-10, 0));
    }

    
    public Vector2 getPos(){
        return position.clone();
    }
    public Collider collider(){
        return collider;
    }
    public BufferedImage sprite(){
        return sprite;
    }
    public InteractionPrompt prompt(){
        return interactionPrompt;
    }
    public Vector2 size(){
        return size.clone();
    }

    public static void createPortal(){
        portal = new ExitPortal();
    }
    public static void deletePortal(){
        portal.destroySelf();
        portal = null;
    }
    public static ExitPortal portal(){
        return portal;
    }
}
