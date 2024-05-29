import java.awt.*;
import java.awt.image.*;

public class RestartButton extends InteractableObject{

    private Vector2 position;
    private BufferedImage sprite;
    private Vector2 size;
    private InteractionPrompt prompt;
    private Collider collider;

    public RestartButton(){
        position = new Vector2(0, 36);
    }

    public void interacted(){
        //send restart to game
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
