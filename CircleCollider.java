
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
}
