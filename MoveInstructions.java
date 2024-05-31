import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MoveInstructions implements DrawableObject{
    private static MoveInstructions Singleton;
    private final Vector2 position;
    private BufferedImage pic;
    private Vector2 size;

    public MoveInstructions(){
        position = new Vector2(0, -10);

        try{
            pic = ImageIO.read(new File("WASD23x15.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        size = new Vector2(1.5f, 1.5f*15/23);
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(position, new Vector2(-size.getX()/2,size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }

    public static void drawAll(Graphics g){
        if(Singleton == null){
            Singleton = new MoveInstructions();
        }
        Singleton.drawMe(g);
    }
}
