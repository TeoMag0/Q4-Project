import java.awt.*;
/*
 * purpose: contain data relevant to the player
 * things to update: figure out collisions for player
 */

public class DummyPlayer implements DrawableObject, Transform {
    private Vector2 position;
    private Vector2 size;
    public final DummyPlayerAppearanceManager appearanceManager;
    public final DummyPlayerAnimator animator;
    private boolean alive;
    private PlayerColor color;
    
    public DummyPlayer(Vector2 position) {
        this.position = position.clone();
        size = Screen.player.size();
        alive = true;

        appearanceManager = new DummyPlayerAppearanceManager(this);
        animator = new DummyPlayerAnimator(this);
        new Thread(animator).start();
    }

    public void drawMe(Graphics g) {
        if(alive){
            appearanceManager.drawMe(g);
        }
    }
    public synchronized void setPosition(Vector2 pos) {
        position = pos.clone();
    }
    public synchronized void moveToPosition(Vector2 pos){
        animator.addNewDestination(pos);
    }
    public synchronized void movePosition(Vector2 deltaPos) {
        position.add(deltaPos);
    }

    public Vector2 getPos() {
        return position.clone();
    }

    public Vector2 size() {
        return size.clone();
    }

    public void setSize(Vector2 s) {
        size = s.clone();
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public PlayerColor color(){
        return color;
    }
    public void setColor(int c){
        switch(c){
            case 0:
                color = PlayerColor.BLUE;
                break;
            case 1:
                color = PlayerColor.GREEN;
                break;
            case 2:
                color = PlayerColor.YELLOW;
                break;
            case 3:
                color = PlayerColor.RED;
                break;
            default:
                color = PlayerColor.BLUE;
                break;
        }
        appearanceManager.movementAnimation.updateColor();
    }
}
