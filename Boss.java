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
        size = new Vector2(.5f, .5f);

        attackManager = new BossAttackManager(this);
        healthManager = new BossHealthManager(this);
        speechBubble = new BossSpeechBubble(this, new Vector2(0, 1));
        appearanceManager = new BossAppearanceManager(this);
    }
    public void start(){

    }

    public void die(){
        new LootItem(position);
        healthManager.drawHealth.setActive(false);
        appearanceManager.setActive(false);
    }
    public void resurrect(){
        appearanceManager.setActive(true);
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

    public void phase2(){
        appearanceManager.phase2();
        size = new Vector2(1.3f, 1.3f);
    }
}
