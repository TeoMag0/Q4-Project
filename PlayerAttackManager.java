import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/*
 * purpose: manage play mouse inputs
 */

public class PlayerAttackManager extends Startable implements MouseInputListener, Runnable{
    private Player player;
    private boolean active;
    private Vector2 mouseWorldPosition;
    private boolean mouseDown;
    private boolean canShoot;
    private float firerate;
    private Thread runThread;

    public PlayerAttackManager(Player player){
        this.player = player;
        active = true;
        mouseDown = false;
        firerate = 5;
        canShoot = true;
    }
    public void start(){
        Screen.Singleton.addMouseListener(this);
        Screen.Singleton.addMouseMotionListener(this);
        runThread = new Thread(this);
        runThread.start();
    }

    public void run(){
        try{
            while(true){
                if(active && mouseDown && canShoot){
                    launchProjectile();
                    canShoot(false);
                }
                if(!canShoot){
                    Thread.sleep((int) (1000f / firerate));
                    canShoot(true);
                }
            }
        }catch(InterruptedException e){

        }
    }
    public void launchProjectile(){
        float speed = 3;
        new PlayerProjectile("Parentheses.png", player.getPos(), .5f, Vector2.multiply(Vector2.difference(mouseWorldPosition, player.getPos()).normalized(), speed));
    }

    public void mouseDragged(MouseEvent e){
        mouseWorldPosition = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
    }
    public void mouseMoved(MouseEvent e){
        
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mousePressed(MouseEvent e) {
        mouseWorldPosition = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
        mouseDown = true;
    }
    
    public void mouseReleased(MouseEvent e) {
        //tilemap building, temporary
        Vector2 coords = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
        /*if(e.getButton() == MouseEvent.BUTTON1){
            //TileMap.Singleton.addTile(coords, TilePic.STONE_BRICK_FLOOR, false);
            //TileMap.Singleton.saveMap();
            //System.out.println(TileMap.Singleton.coordsToRC(coords));
        }else if(e.getButton() == MouseEvent.BUTTON3) {
            TileMap.Singleton.addTile(coords, TilePic.STONE_WALL, true);
            TileMap.Singleton.saveMap();
        }else if(e.getButton() == MouseEvent.BUTTON2){
            TileMap.Singleton.removeTile(coords);
            TileMap.Singleton.saveMap();
        }*/
        mouseDown = false;
    }
    


    public void mouseEntered(MouseEvent e) {

    }
    
    public void mouseExited(MouseEvent e) {

    }

    public void setActive(boolean active){
        this.active = active;
    }
    private synchronized void canShoot(boolean canShoot){
        this.canShoot = canShoot;
    }
}
