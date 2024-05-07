import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/*
 * purpose: manage play mouse inputs
 */

public class PlayerAttackManager extends Startable implements MouseInputListener{
    private Player player;

    public PlayerAttackManager(Player player){
        this.player = player;
    }
    public void start(){
        Screen.Singleton.addMouseListener(this);
        Screen.Singleton.addMouseMotionListener(this);
    }

    public void mouseDragged(MouseEvent e){

    }
    public void mouseMoved(MouseEvent e){
        
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mousePressed(MouseEvent e) {

    }
    
    public void mouseReleased(MouseEvent e) {
        //tilemap building, temporary
        Vector2 coords = Screen.getWorldCoords(new Vector2(e.getX(), e.getY()));
        if(e.getButton() == MouseEvent.BUTTON1){
            TileMap.Singleton.addTile(coords, TilePic.STONE_BRICK_FLOOR, false);
            TileMap.Singleton.saveMap();
        }else if(e.getButton() == MouseEvent.BUTTON3) {
            TileMap.Singleton.addTile(coords, TilePic.JAIL_BARS, true);
            TileMap.Singleton.saveMap();
        }else if(e.getButton() == MouseEvent.BUTTON2){
            TileMap.Singleton.removeTile(coords);
            TileMap.Singleton.saveMap();
        }
    }
    


    public void mouseEntered(MouseEvent e) {

    }
    
    public void mouseExited(MouseEvent e) {

    }
}
