import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class BossAppearanceManager implements DrawableObject{
    
    private Boss boss;
    private BufferedImage startingSprite;
    private BufferedImage sprite;
    private boolean active;
    public final BossAnimationManager animationManager;

    public BossAppearanceManager(Boss boss){
        this.boss = boss;
        active = true;

        try{
            startingSprite = ImageIO.read(new File("Boss0.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        sprite = startingSprite;
        animationManager = new BossAnimationManager(boss);
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

    public void phase2(){
        animationManager.phase2();
    }
    public BufferedImage sprite(){
        return sprite;
    }
    public void setSprite(BufferedImage sprite){
        this.sprite = sprite;
    }
}
