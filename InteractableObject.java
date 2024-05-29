import java.awt.*;
import java.awt.image.*;

public abstract class InteractableObject implements DrawableObject, Transform, HasCollider{

    private static final DLList<InteractableObject> objectList = new DLList<>();
    private static final DLList<InteractableObject> toRemove = new DLList<>();
    
    public InteractableObject(){
        objectList.add(this);
    }
    public void onCollisionEnter(Collider col){

    }

    public void drawMe(Graphics g){
        Vector2 size = size();
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-size.getX()/2,size.getY()/2)));
        g.drawImage(sprite(), drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);

        if(playerTouching()){
            prompt().drawMe(g);
        }
    }

    public boolean playerTouching(){
        if(!Screen.player.isAlive()){
            return false;
        }
        return Collider.checkCollision(collider(), Screen.player.collisionManager.collider());
    }

    public void destroySelf(){
        toRemove.add(this);
        Collider.removeCollider(collider());
    }

    public abstract Vector2 size();
    public abstract BufferedImage sprite();
    public abstract InteractionPrompt prompt();
    public abstract Collider collider();
    public abstract void interacted();

    public static void drawAll(Graphics g){
        while(toRemove.size() != 0){
            InteractableObject obj = toRemove.remove(0);
            objectList.remove(obj);
        }
        for(InteractableObject each : objectList){
            each.drawMe(g);
        }
    }
    public static void checkInteractions(){
        for(InteractableObject each : objectList){
            if(each.playerTouching()){
                each.interacted();
            }
        }
    }
}
