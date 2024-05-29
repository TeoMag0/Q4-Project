import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HeartPickup extends InteractableObject implements Runnable{

    private Vector2 position;
    private Vector2 size;
    private BufferedImage sprite;
    private BoxCollider collider;
    private InteractionPrompt interactionPrompt;
    private static final BufferedImage[] anim = new BufferedImage[17];
    
    public HeartPickup(Vector2 position){
        super();
        size = new Vector2(.5f,.5f);
        this.position = position.clone();

        collider = new BoxCollider(this, size, ColliderPurpose.INTERACTABLE);

        interactionPrompt = new InteractionPrompt(this, new Vector2(0, size.getY()/2+.2f), "Pick Up");

        sprite = anim[0];
        new Thread(this).start();
    }


    public void interacted(){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Vector2>(position, Packet.PLAYER_HEALED));
        Screen.player.healthManager.heal(2);
        destroySelf();
    }

    public void run(){
        try{
            for(int i=0;i<anim.length;i++){
                sprite = anim[i];
                Thread.sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void setUpSprites(){
        try{
            for(int i=1;i<=anim.length;i++){
                if(i == 15 || i == 17){
                    anim[i-1] = ImageIO.read(new File("FullHeart.png"));
                }else{
                    anim[i-1] = ImageIO.read(new File("Heart"+i+".png"));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
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