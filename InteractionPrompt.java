import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class InteractionPrompt implements DrawableObject, Transform {
    
    private Transform parent;
    private Vector2 offset;
    private BufferedImage sprite;

    public InteractionPrompt(Transform parent, Vector2 offset){
        this.parent = parent;
        this.offset = offset.clone();

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g){

    }
    public Vector2 getPos(){
        return Vector2.sum(parent.getPos(), offset);
    }
}
