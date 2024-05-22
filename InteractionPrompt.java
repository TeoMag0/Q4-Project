import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class InteractionPrompt implements DrawableObject, Transform {
    
    private Transform parent;
    private Vector2 offset;
    private BufferedImage sprite;
    private Vector2 size;

    public InteractionPrompt(Transform parent, Vector2 offset){
        this.parent = parent;
        this.offset = offset.clone();

        size = new Vector2(.3f, .3f);

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getX()/2,size.getY()/2)));
        g.drawImage(sprite, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }
    public Vector2 getPos(){
        return Vector2.sum(parent.getPos(), offset);
    }
}
