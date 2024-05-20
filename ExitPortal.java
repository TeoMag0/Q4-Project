import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ExitPortal implements DrawableObject, Transform, HasCollider{

    private Vector2 locationRC;
    private Vector2 position;
    private Vector2 size;
    private BufferedImage sprite;
    
    public ExitPortal(){
        size = new Vector2(1,1);
        locationRC = Vector2.zero();
        position = TileMap.Singleton.rcToCoords(locationRC);

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void onCollisionEnter(Collider col){

    }

    public void drawMe(Graphics g){

    }
    public Vector2 getPos(){
        return position;
    }
}
