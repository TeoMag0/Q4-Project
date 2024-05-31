import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Balloon implements Transform, DrawableObject{
    private Vector2 position;
    private BufferedImage pic;
    private Vector2 size;

    public Balloon(Vector2 position, String colorFirstLetter){
        this.position = position.clone();
        size = new Vector2(.5f, 1f);

        try{
            pic = ImageIO.read(new File("Balloon"+colorFirstLetter+"1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getX()/2, size.getY()/2)));
        g.drawImage(pic, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);
    }

    public Vector2 getPos(){
        return position.clone();
    }
}
