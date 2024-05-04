import java.awt.*;
/*
 * purpose: contain data relevant to the player
 * things to update: figure out collisions for player
 */

public class Player extends Startable implements DrawableObject, Transform{
    private Vector2 position;
    private Vector2 size;
    public final PlayerMovement movementManager;
    public final PlayerAppearanceManager appearanceManager;
    public final PlayerConnectionManager connectionManager;
    private Vector2 redirectionVector; //redirects player if they are touching a wall; get normal force direction from collider, then dot deltapos with the normal of the normal force?

    public Player(Vector2 position, float speed){
        this.position = position.clone();
        size = new Vector2(.3f, 0);
        redirectionVector = Vector2.zero();

        movementManager = new PlayerMovement(this, speed);
        connectionManager = new PlayerConnectionManager(this);
        appearanceManager = new PlayerAppearanceManager(this);
    }
    public void start(){
        new Thread(movementManager).start();
        new Thread(connectionManager).start();
    }

    public void drawMe(Graphics g){
        appearanceManager.drawMe(g);
    }

    public void movePosition(Vector2 deltaPos){
        //if(not colliding on one side)
        position.add(deltaPos);
    }
    public Vector2 getPos(){
        return position.clone();
    }
    public Vector2 size(){
        return size.clone();
    }
    public void setSize(Vector2 s){
        size = s.clone();
    }
}
