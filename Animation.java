import java.awt.*;

/*
 * purpose: draws all animations
 */

public abstract class Animation implements DrawableObject, Runnable{
    private static final DLList<Animation> animations = new DLList<>();

    public Animation(){
        animations.add(this);
    }

    public static void drawAll(Graphics g){
        for(Animation each : animations){
            each.drawMe(g);
        }
    }

    public static void removeAnimation(Animation anim){
        animations.remove(anim);
    }
}
