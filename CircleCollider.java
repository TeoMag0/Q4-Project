import java.awt.*;

/*
 * purpose: provide collision detection in the shape of a circle
 */

public class CircleCollider extends Collider{
    private float radius;
    
    public CircleCollider(HasCollider parent, float radius, ColliderPurpose purpose){
        super(parent, purpose, Shape.CIRCLE);
        this.radius = radius;
    }

    public float radius(){
        return radius;
    }

    public void drawMe(Graphics g){
        g.setColor(Color.GREEN);
        Vector2 drawPos = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-radius, radius)));
        g.drawOval(drawPos.intX(), drawPos.intY(), Screen.toPixels(radius*2), Screen.toPixels(radius*2));
    }
}
