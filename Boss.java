import java.awt.*;

/*
 * manages everything to do with the boss
 */

public class Boss extends Startable implements DrawableObject, Transform{
    
    private Vector2 size;
    private Vector2 position;
    public final BossAttackManager attackManager;
    public final BossHealthManager healthManager;
    public final BossSpeechBubble speechBubble;
    public final BossAppearanceManager appearanceManager;

    public Boss(Vector2 position){
        this.position = position;
        size = new Vector2(2, 2);

        attackManager = new BossAttackManager(this);
        healthManager = new BossHealthManager(this);
        speechBubble = new BossSpeechBubble(this, new Vector2(0, 1));
        appearanceManager = new BossAppearanceManager(this);
    }
    public void start(){

    }

    public void dropLoot(){
        new LootItem(position);
    }
    
    public void drawMe(Graphics g){
        appearanceManager.drawMe(g);
        speechBubble.drawMe(g);
    }
    public Vector2 getPos(){
        return position.clone();
    }

    public Vector2 size(){
        return size.clone();
    }
}
