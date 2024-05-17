import java.awt.*;

/*
 * manages everything to do with the boss
 */

public class Boss extends Startable implements DrawableObject, Transform{
    
    private float size;
    private Vector2 position;
    public final BossAttackManager attackManager;
    public final BossHealthManager healthManager;
    public final BossSpeechBubble speechBubble;

    public Boss(Vector2 position){
        this.position = position;
        size = 2;

        attackManager = new BossAttackManager(this);
        healthManager = new BossHealthManager(this);
        speechBubble = new BossSpeechBubble(this, new Vector2(0, 2));
    }
    public void start(){

    }
    
    public void drawMe(Graphics g){
        speechBubble.drawMe(g);
    }
    public Vector2 getPos(){
        return position.clone();
    }

    public Vector2 size(){
        return new Vector2(size, size);
    }
}
