import java.awt.event.*;

/*
 * purpose: give movement commands
 */

public class PlayerMovement implements KeyListener, Runnable{

    private boolean a,w,d,s;
    private float speed;
    private Player player;

    public PlayerMovement(Player player, float speed){
        this.speed = speed;
        this.player = player;
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
    public void run(){
        while(true){
            Vector2 direction = Vector2.zero();
            if(a){
                direction.add(new Vector2(-1,0));
            }if(w){
                direction.add(new Vector2(0, 1));
            }if(d){
                direction.add(new Vector2(1, 0));
            }if(s){
                direction.add(new Vector2(0, -1));
            }
            if(!direction.equals(Vector2.zero())){
                player.movePosition(Vector2.multiply(direction.normalized(), speed));
                Screen.Singleton.repaint();
            }
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
