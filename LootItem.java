import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class LootItem extends InteractableObject{

    private Vector2 position;
    private Vector2 size;
    private BufferedImage sprite;
    private BoxCollider collider;
    private InteractionPrompt interactionPrompt;
    
    public LootItem(Vector2 position){
        super();
        size = new Vector2(.5f,.5f);
        this.position = position.clone();

        collider = new BoxCollider(this, size, ColliderPurpose.INTERACTABLE);

        interactionPrompt = new InteractionPrompt(this, new Vector2(0, size.getY()/2+.2f));

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void interacted(){
        destroySelf();
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
}