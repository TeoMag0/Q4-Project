import java.awt.event.*;

/*
 * purpose: give movement commands
 * notes: works off of collision timer to make player colisions align
 */

public class PlayerMovement extends Startable implements KeyListener{

    private boolean a,w,d,s;
    private float speed;
    private Player player;
    private final float movementDeltaTime;
    private boolean active;

    public PlayerMovement(Player player, float speed){
        active = true;
        this.speed = speed;
        this.player = player;
        movementDeltaTime = 0.01f;
    }
    public void start(){
        Screen.Singleton.addKeyListener(this);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            a = true;
        }else if(e.getKeyCode() == KeyEvent.VK_W){
            w = true;
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            d = true;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            s = true;
        }

        //tilemap testing
        else if(e.getKeyCode() == KeyEvent.VK_0){
            TileMap.Singleton.addTile(player.getPos(), TilePic.STONE_BRICK_FLOOR, false);
            TileMap.Singleton.saveMap();
        }else if(e.getKeyCode() == KeyEvent.VK_9) {
            TileMap.Singleton.addTile(player.getPos(), TilePic.STONE_WALL, true);
            TileMap.Singleton.saveMap();
        }
    }
    public void keyTyped(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_A) {
            a = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            w = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            d = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            s = false;
        }
    }

    public void movePlayer(){
        Vector2 direction = Vector2.zero();
        if (a) {
            direction.add(new Vector2(-1, 0));
        }
        if (w) {
            direction.add(new Vector2(0, 1));
        }
        if (d) {
            direction.add(new Vector2(1, 0));
        }
        if (s) {
            direction.add(new Vector2(0, -1));
        }
        if (!direction.equals(Vector2.zero()) && active) {
            player.movePosition(Vector2.multiply(direction.normalized(), speed * movementDeltaTime));
            Screen.Singleton.repaint();
        }
    }
}
