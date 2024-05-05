import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: draw all player UI stuff
 * notes: DOESN'T USE WORLD POINTS, IT'S UI :skull:
 */

public class PlayerUIManager implements DrawableObject{
    private BufferedImage[] hearts;
    private Player player;

    public PlayerUIManager(Player player){
        this.player = player;

        hearts = new BufferedImage[3];
        try{
            hearts[0] = ImageIO.read(new File("FullHeart.png"));
            hearts[1] = ImageIO.read(new File("HalfHeart1.png"));
            hearts[2] = ImageIO.read(new File("EmptyHeart.png"));
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void drawMe(Graphics g){
        drawHearts(g);
    }
    private void drawHearts(Graphics g){
        Vector2 heartPixelSize = new Vector2(75,75);
        Vector2 heartLoc = new Vector2(10, 10);
        int heartSpacing = 10;

        int health = player.healthManager.health();
        int healthToDraw = health;
        for(int i=player.healthManager.maxHealth; i>0;i-=2){
            BufferedImage heartToDraw;
            if(healthToDraw >= 2){
                heartToDraw = hearts[0];
            }else if(healthToDraw == 1){
                heartToDraw = hearts[1];
            }else{
                heartToDraw = hearts[2];
            }
            g.drawImage(heartToDraw, heartLoc.intX(), heartLoc.intY(), heartPixelSize.intX(), heartPixelSize.intY(), null);
            heartLoc.add(new Vector2(heartSpacing+heartPixelSize.getX(), 0));
            healthToDraw-=2;
        }
    }
}