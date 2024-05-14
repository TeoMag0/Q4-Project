import java.awt.event.*;

/*
 * purpose: give movement commands
 * notes: works off of collision timer to make player colisions align
 */

public class PlayerKeyInput extends Startable implements KeyListener{

    private boolean a,w,d,s;
    private float speed;
    private Player player;
    private boolean active;

    public PlayerKeyInput(Player player, float speed){
        active = true;
        this.speed = speed;
        this.player = player;
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
            player.appearanceManager.movementAnimation.moving(true);
        }else if(e.getKeyCode() == KeyEvent.VK_W){
            w = true;
            player.appearanceManager.movementAnimation.moving(true);
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            d = true;
            player.appearanceManager.movementAnimation.moving(true);
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            s = true;
            player.appearanceManager.movementAnimation.moving(true);
        }else if(e.getKeyCode() == KeyEvent.VK_P){
            Screen.drawColliders = !Screen.drawColliders;
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
        if(!(a || w || s || d)){
            player.appearanceManager.movementAnimation.moving(false);
        }
    }

    public void movePlayer(float deltaTimeMS){
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
            player.movePosition(Vector2.multiply(direction.normalized(), speed * 1f*deltaTimeMS/1000));
            Screen.Singleton.repaint();
        }
    }
}
