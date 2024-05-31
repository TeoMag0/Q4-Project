import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class RestartButton extends InteractableObject{

    private Vector2 position;
    private BufferedImage sprite;
    private Vector2 size;
    private InteractionPrompt prompt;
    private Collider collider;

    public RestartButton(){
        position = TileMap.Singleton.rcToCoords(new Vector2(0, 40));

        try{
            sprite = ImageIO.read(new File("RedPortal.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        size = new Vector2(1f,1f);
        prompt = new InteractionPrompt(this, new Vector2(0, .3f), "Restart");
        collider = new BoxCollider(this, size, ColliderPurpose.INTERACTABLE);
    }

    public void interacted(){
        ClientGameManager.Singleton.restartGame();
        ConnectionManager.Singleton.sendObject(new NetworkObject<Boolean>(null, Packet.RESTART_GAME));
    }

    public Vector2 getPos() {
        return position.clone();
    }
    public Vector2 size() {
        return size.clone();
    }
    public BufferedImage sprite() {
        return sprite;
    }
    public InteractionPrompt prompt() {
        return prompt;
    }
    public Collider collider() {
        return collider;
    }
}
