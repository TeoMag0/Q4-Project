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
    private boolean active;
    public final PlayerMovementAnimation movementAnimation;
    
    public PlayerAppearanceManager(Player player){
        active = true;

        try{
            playerSprite = ImageIO.read(new File("BlankSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        this.player = player;
        float playerSpriteHeight = player.size().getX()*playerSprite.getHeight()/playerSprite.getWidth();
        player.setSize(new Vector2(player.size().getX(), playerSpriteHeight));

        movementAnimation = new PlayerMovementAnimation(player);
    }

    public void drawMe(Graphics g){
        if(!active){
            return;
        }
        Vector2 playerSize = player.size();
        Vector2 playerPixelSize = new Vector2(Screen.toPixels(playerSize.getX()), Screen.toPixels(playerSize.getY()));
        Vector2 playerPixelDrawpoint = Vector2.multiply(Vector2.difference(Screen.screenPixelDimensions, playerPixelSize), 1f/2);
        g.drawImage(playerSprite, playerPixelDrawpoint.intX(), playerPixelDrawpoint.intY(), playerPixelSize.intX(), playerPixelSize.intY(), null);
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void setSprite(BufferedImage image){
        playerSprite = image;
    }
    public BufferedImage sprite(){
        return playerSprite;
    }
}

enum PlayerColor{
    BLUE,
    GREEN,
    YELLOW,
    RED
}
