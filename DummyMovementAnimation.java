import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class DummyMovementAnimation {

    private DummyPlayer player;
    private BufferedImage sprites[];
    
    public DummyMovementAnimation(DummyPlayer player){
        this.player = player;

        sprites = new BufferedImage[2];
    }

    public void updateColor(){
        try {
            String color = "";
            switch (player.color()) {
                case BLUE:
                    color = "Blue";
                    break;
                case GREEN:
                    color = "Green";
                    break;
                case YELLOW:
                    color = "Yellow";
                    break;
                case RED:
                    color = "Red";
                    break;
            }
            sprites[0] = ImageIO.read(new File(String.format("%s1.png", color)));
            sprites[1] = ImageIO.read(new File(String.format("%s2.png", color)));

            player.appearanceManager.setSprite(sprites[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
