import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: manage the sprite/appearance of the player
 */

public class PlayerAppearanceManager implements DrawableObject {
    private Player player;
    private BufferedImage playerSprite;
    
    public PlayerAppearanceManager(Player player){

        try{
            playerSprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            System.out.println(e);
        }

        this.player = player;
        float playerSpriteHeight = player.size().getX()*playerSprite.getHeight()/playerSprite.getWidth();
        player.setSize(new Vector2(player.size().getX(), playerSpriteHeight));
    }

    public void drawMe(Graphics g){
        Vector2 playerSize = player.size();
        Vector2 playerPixelSize = new Vector2(Screen.toPixels(playerSize.getX()), Screen.toPixels(playerSize.getY()));
        Vector2 playerPixelDrawpoint = Vector2.multiply(Vector2.difference(Screen.screenPixelDimensions, playerPixelSize), 1f/2);
        g.drawImage(playerSprite, playerPixelDrawpoint.intX(), playerPixelDrawpoint.intY(), playerPixelSize.intX(), playerPixelSize.intY(), null);
    }
}
