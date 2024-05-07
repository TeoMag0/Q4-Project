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
    private float firerate;
    private Thread curFiringThread;

    public PlayerAttackManager(Player player){
        this.player = player;
        active = true;
        mouseDown = false;
        firerate = 1;
    }
    public void start(){
        Screen.Singleton.addMouseListener(this);
        Screen.Singleton.addMouseMotionListener(this);
    }

    public void run(){
        try{
            while(mouseDown){
                if(active){
                    launchProjectile();
                }
                Thread.sleep((int)(1000f/firerate));
            }
        }catch(InterruptedException e){

        }
    }
    public void launchProjectile(){
        float speed = 0;
        new PlayerProjectile("Parentheses.png", player.getPos(), 1f, Vector2.multiply(Vector2.difference(mouseWorldPosition, player.getPos()).normalized(), speed));
    }

    public void mouseDragged(MouseEvent e){
        mouseWorldPosition = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
    }
    public void mouseMoved(MouseEvent e){
        
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        mouseWorldPosition = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
        curFiringThread = new Thread(this);
        curFiringThread.start();
    }
    
    public void mouseReleased(MouseEvent e) {
        //tilemap building, temporary
        Vector2 coords = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
        if(e.getButton() == MouseEvent.BUTTON1){
            //TileMap.Singleton.addTile(coords, TilePic.STONE_BRICK_FLOOR, false);
            //TileMap.Singleton.saveMap();
            //System.out.println(TileMap.Singleton.coordsToRC(coords));
        }else if(e.getButton() == MouseEvent.BUTTON3) {
            TileMap.Singleton.addTile(coords, TilePic.JAIL_BARS, true);
            TileMap.Singleton.saveMap();
        }else if(e.getButton() == MouseEvent.BUTTON2){
            TileMap.Singleton.removeTile(coords);
            TileMap.Singleton.saveMap();
        }
        mouseDown = false;
    }
    


    public void mouseEntered(MouseEvent e) {

    }
    
    public void mouseExited(MouseEvent e) {

    }

    public void setActive(boolean active){
        this.active = active;
    }
}
