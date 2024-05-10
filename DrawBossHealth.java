import java.awt.*;

public class DrawBossHealth {

    private Vector2 screenPos = new Vector2(640, 600);
    private int screenWidth = 800;
    private int screenHeight = 50;
    private boolean active = false;
    private BossHealthManager healthManager;

    public DrawBossHealth(BossHealthManager healthManager){
        screenPos = new Vector2(640, 600);
        screenWidth = 800;
        screenHeight = 50;
        active = false;
        this.healthManager = healthManager;
    }
    

    public void drawBossHealth(Graphics g){
        if(!active){
            return;
        }
        int bossHealth = healthManager.health();
        int maxHealth = healthManager.maxHealth();

        Vector2 drawPoint = Vector2.sum(screenPos, new Vector2(-screenWidth/2, -screenHeight/2));

        g.setColor(Color.WHITE);
        g.fillRoundRect(drawPoint.intX(), drawPoint.intY(), screenWidth, screenHeight, 15, 15);

        int healthLength = (int)(1f*screenWidth*bossHealth/maxHealth);
        g.setColor(Color.RED);
        g.fillRoundRect(drawPoint.intX(), drawPoint.intY(), healthLength, screenHeight, 15, 15);
    }

    public void setActive(boolean active){
        this.active = active;
    }
}