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
    public final PlayerCollisionManager collisionManager;
    public final PlayerHealthManager healthManager;
    public final PlayerUIManager uiManager;
    private Vector2 redirectionVector; //redirects player if they are touching a wall; get normal force direction from collider, then dot deltapos with the normal of the normal force

    public Player(Vector2 position, float speed){
        this.position = position.clone();
        size = new Vector2(.3f, 0);
        redirectionVector = Vector2.zero();

        movementManager = new PlayerMovement(this, speed);
        connectionManager = new PlayerConnectionManager(this);
        appearanceManager = new PlayerAppearanceManager(this);
        collisionManager = new PlayerCollisionManager(this, size.getX()/2);
        healthManager = new PlayerHealthManager(this, 6);
        uiManager = new PlayerUIManager(this);
    }
    public void start(){
        new Thread(connectionManager).start();
    }

    public void drawMe(Graphics g){
        appearanceManager.drawMe(g);
        uiManager.drawMe(g);
    }

    public void movePosition(Vector2 deltaPos){
        if(!redirectionVector.equals(Vector2.zero()) && Vector2.dot(deltaPos, redirectionVector) < 0){
            //redir normal is parallel to the surface
            Vector2 redirNormal = new Vector2(-redirectionVector.getY(), redirectionVector.getX());
            deltaPos = Vector2.multiply(redirNormal, Vector2.dot(deltaPos, redirNormal));
        }
        position.add(deltaPos);
        redirectionVector = Vector2.zero();
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
    public void setRedirectionVector(Vector2 vector){
        redirectionVector = vector.clone();
    }
    public void die(){
        appearanceManager.setActive(false);
        collisionManager.setActive(false);
        movementManager.setActive(false);
        position = Vector2.zero();
        Screen.setPixelsPerUnit(50);
        connectionManager.die();
    }
    public void resurrect(Vector2 pos){
        appearanceManager.setActive(true);
        collisionManager.setActive(true);
        movementManager.setActive(true);
        position = pos.clone();
        Screen.setPixelsPerUnit(100);
        healthManager.resetHealth();
        connectionManager.resurrect();
    }
}
