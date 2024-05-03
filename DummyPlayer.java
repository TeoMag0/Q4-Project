import java.awt.*;
/*
 * purpose: contain data relevant to the player
 * things to update: figure out collisions for player
 */

public class DummyPlayer implements DrawableObject {
    private Vector2 position;
    private Vector2 size;
    private final DummyPlayerAppearanceManager appearanceManager;
    private final DummyPlayerAnimator animator;
    
    public DummyPlayer(Vector2 position) {
        this.position = position.clone();
        size = new Vector2(.3f, 0);

        appearanceManager = new DummyPlayerAppearanceManager(this);
        animator = new DummyPlayerAnimator(this);
        new Thread(animator).start();
    }

    public void drawMe(Graphics g) {
        appearanceManager.drawMe(g);
    }
    public void setPosition(Vector2 pos) {
        position = pos.clone();
    }
    public void moveToPosition(Vector2 pos){
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
}
