import java.awt.*;

/*
 * manages everything to do with the boss
 */

public class Boss extends Startable implements DrawableObject, Transform{
    
    private float size;
    private Vector2 position;
    public final BossAttackManager attackManager;

    public Boss(Vector2 position){
        this.position = position;
        size = 2;

        attackManager = new BossAttackManager(this);
    }
    public void start(){

    }
    
    public void drawMe(Graphics g){

    }
    public Vector2 getPos(){
        return position.clone();
    }
}
