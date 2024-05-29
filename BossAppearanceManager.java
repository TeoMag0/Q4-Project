import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class BossAppearanceManager implements DrawableObject{
    
    private Boss boss;
    private BufferedImage sprite;

    public BossAppearanceManager(Boss boss){
        this.boss = boss;

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(boss.getPos(), new Vector2(-boss.size().getX()/2, boss.size().getY()/2)));
        g.drawImage(sprite, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(boss.size().getX()), Screen.toPixels(boss.size().getY()), null);
    }
}
