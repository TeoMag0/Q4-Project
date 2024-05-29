import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class DrawBossHealth {

    private Vector2 screenPos = new Vector2(640, 600);
    private int screenWidth = 800;
    private int screenHeight = 50;
    private boolean active = false;
    private BossHealthManager healthManager;
    private BufferedImage healthBar;

    public DrawBossHealth(BossHealthManager healthManager){
        screenPos = new Vector2(640, 600);
        screenWidth = 800;
        screenHeight = 50;
        active = false;
        this.healthManager = healthManager;

        try{
            healthBar = ImageIO.read(new File("BossHealthBar.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    public void drawBossHealth(Graphics g){
        if(!active){
            return;
        }
        int bossHealth = healthManager.health();
        int maxHealth = healthManager.maxHealth();

        Vector2 drawPoint = Vector2.sum(screenPos, new Vector2(-screenWidth/2, -screenHeight/2));

        //g.setColor(Color.WHITE);
        //g.fillRoundRect(drawPoint.intX(), drawPoint.intY(), screenWidth, screenHeight, 15, 15);

        int healthLength = (int)(1f*screenWidth*bossHealth/maxHealth);
        g.setColor(Color.RED);
        g.fillRoundRect(drawPoint.intX(), drawPoint.intY(), healthLength, screenHeight, 15, 15);

        if(healthManager.invulnerable()){
            g.setColor(new Color(255,255, 255, 100));
            g.fillRect(drawPoint.intX(), drawPoint.intY(), healthLength, screenHeight);
        }

        g.drawImage(healthBar, drawPoint.intX(), drawPoint.intY(), screenWidth, screenHeight, null);
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
