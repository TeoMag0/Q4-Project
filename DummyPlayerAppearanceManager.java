import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: manage the sprite/appearance of the player
 */

public class DummyPlayerAppearanceManager implements DrawableObject {
    private DummyPlayer player;
    private BufferedImage playerSprite;

    public DummyPlayerAppearanceManager(DummyPlayer player) {

        try {
            playerSprite = ImageIO.read(new File("BlankSprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.player = player;
        float playerSpriteHeight = player.size().getX() * playerSprite.getHeight() / playerSprite.getWidth();
        player.setSize(new Vector2(player.size().getX(), playerSpriteHeight));
    }

    public void drawMe(Graphics g) {
        Vector2 playerSize = player.size();
        Vector2 playerPixelSize = new Vector2(Screen.toPixels(playerSize.getX()), Screen.toPixels(playerSize.getY()));
        Vector2 playerPixelDrawpoint = Screen.getScreenCoords(Vector2.difference(player.getPos(), new Vector2(playerSize.getX()/2, -playerSize.getY()/2)));
        g.drawImage(playerSprite, playerPixelDrawpoint.intX(), playerPixelDrawpoint.intY(), playerPixelSize.intX(), playerPixelSize.intY(), null);
    }
}
