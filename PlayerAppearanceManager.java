import java.awt.*;

/*
 * manage the sprite/appearance of the player
 */

public class PlayerAppearanceManager implements DrawableObject {
    private Player player;
    private Vector2 playerSize;
    
    public PlayerAppearanceManager(Player player){
        this.player = player;
        playerSize = new Vector2(1f, 1f);//later make height the proper ration compared to width
    }
    public void drawMe(Graphics g){
        Vector2 playerPixelSize = new Vector2(Screen.toPixels(playerSize.getX()), Screen.toPixels(playerSize.getY()));
        Vector2 playerPixelDrawpoint = Vector2.multiply(Vector2.difference(Screen.screenPixelDimensions, playerPixelSize), 1f/2);
        g.fillOval(playerPixelDrawpoint.intX(), playerPixelDrawpoint.intY(), playerPixelSize.intX(), playerPixelSize.intY());
    }
}
