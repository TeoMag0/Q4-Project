import java.awt.*;
/*
 * purpose: contain data relevant to the player
 * things to update: figure out collisions for player
 */

public class Player implements DrawableObject{
    private Vector2 position;
    private PlayerMovement movementManager;
    private PlayerAppearanceManager appearanceManager;

    public Player(Vector2 position, float speed){
        this.position = position.clone();

        movementManager = new PlayerMovement(this, speed);
        new Thread(movementManager).start();

        appearanceManager = new PlayerAppearanceManager(this);
    }

    public void drawMe(Graphics g){
        appearanceManager.drawMe(g);
    }

    public void sendPosition(){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Vector2>(position, Packet.PLAYERPOS));
    }
    public void movePosition(Vector2 deltaPos){
        //if(not colliding on one side)
        position.add(deltaPos);
    }
    public Vector2 getPos(){
        return position.clone();
    }

    public PlayerMovement movementManager(){
        return movementManager;
    }
}
