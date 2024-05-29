import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class BossAppearanceManager implements DrawableObject{
    
    private Boss boss;
    private BufferedImage sprite;
    private boolean active;

    public BossAppearanceManager(Boss boss){
        this.boss = boss;
        active = true;

        try{
            sprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g){
        if(!active){
            return;
        }
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(boss.getPos(), new Vector2(-boss.size().getX()/2, boss.size().getY()/2)));
        g.drawImage(sprite, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(boss.size().getX()), Screen.toPixels(boss.size().getY()), null);
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
