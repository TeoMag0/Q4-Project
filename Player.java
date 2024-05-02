import java.awt.*;
/*
 * purpose: contain data relevant to the player
 * things to update: figure out collisions for player
 */

public class Player implements DrawableObject{
    private Vector2 position;
    private Vector2 size;
    private PlayerMovement movementManager;
    private PlayerAppearanceManager appearanceManager;

    public Player(Vector2 position, float speed){
        this.position = position.clone();
        size = new Vector2(1, -1);

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
    public Vector2 size(){
        return size.clone();
    }
    public void setSize(Vector2 s){
        size = s.clone();
    }
}
