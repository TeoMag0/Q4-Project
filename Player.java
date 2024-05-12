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
    public final PlayerAttackManager attackManager;
    private final DLList<Vector2> redirectionVectors; //redirects player if they are touching a wall; get normal force direction from collider, then dot deltapos with the normal of the normal force
    private final float inBossRoomYThresholdRC;
    private boolean isInBossRoom;

    public Player(Vector2 position, float speed){
        this.position = position.clone();
        size = new Vector2(.3f, 0);
        redirectionVectors = new DLList<>();
        inBossRoomYThresholdRC = -3;
        isInBossRoom = false;

        movementManager = new PlayerMovement(this, speed);
        connectionManager = new PlayerConnectionManager(this);
        appearanceManager = new PlayerAppearanceManager(this);
        collisionManager = new PlayerCollisionManager(this, size.getX()/2);
        healthManager = new PlayerHealthManager(this, 6);
        uiManager = new PlayerUIManager(this);
        attackManager = new PlayerAttackManager(this);
    }
    public void start(){
        new Thread(connectionManager).start();
    }

    public void drawMe(Graphics g){
        appearanceManager.drawMe(g);
    }

    public void movePosition(Vector2 deltaPos){
        if(redirectionVectors.size() > 0){
            for(Vector2 each : redirectionVectors){
                if(Vector2.dot(deltaPos, each) < 0){
                    // redir normal is parallel to the surface
                    Vector2 redirNormal = new Vector2(-each.getY(), each.getX()).normalized();
                    deltaPos = Vector2.multiply(redirNormal, Vector2.dot(deltaPos, redirNormal));
                }
            }
        }

        position.add(deltaPos);
        redirectionVectors.clear();

        updateIsInBossRoom();
    }
    public void moveTransform(Vector2 deltaPos){
        position.add(deltaPos);
    }
    public Vector2 getPos(){
        return position.clone();
    }
    public void setPos(Vector2 pos){
        position = pos.clone();
    }
    public Vector2 size(){
        return size.clone();
    }
    public void setSize(Vector2 s){
        size = s.clone();
    }
    public void addRedirectionVector(Vector2 vector){
        redirectionVectors.add(vector);
    }
    public void die(){
        appearanceManager.setActive(false);
        collisionManager.setActive(false);
        movementManager.setActive(false);
        position = Vector2.zero();
        Screen.setPixelsPerUnit(75);
        connectionManager.die();
        attackManager.setActive(false);
    }
    public void resurrect(Vector2 pos){
        appearanceManager.setActive(true);
        collisionManager.setActive(true);
        movementManager.setActive(true);
        position = pos.clone();
        Screen.setPixelsPerUnit(100);
        healthManager.resetHealth();
        connectionManager.resurrect();
        attackManager.setActive(true);
    }

    private void updateIsInBossRoom(){
        if (ClientGameManager.Singleton.gameState() == GameState.GET_IN_ROOM) {
            float yThreshold = TileMap.Singleton.rcToCoords(new Vector2(0, inBossRoomYThresholdRC)).getY();
            if (position.getY() >= yThreshold && !isInBossRoom) {
                connectionManager.isInBossRoom(true);
                isInBossRoom = true;
            } else if (position.getY() < yThreshold && isInBossRoom) {
                connectionManager.isInBossRoom(false);
                isInBossRoom = false;
            }
        }
    }
}
