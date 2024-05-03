
/*
 * purpose: Responsible for all interactions between colliders
 */

public abstract class Collider implements Transform{
    private Shape shape;
    private Transform parent;
    private static final MyArrayList<Collider> allColliders = new MyArrayList<>();

    public Collider(Transform parent){
        this.parent = parent;
        allColliders.add(this);
    }

    public Shape shape(){
        return shape;
    }
    public Vector2 getPos(){
        return parent.getPos();
    }
    public abstract void onCollisionEnter(Collider col);

    public static void checkCollision(Collider col1, Collider col2){
        if(col1.shape() == Shape.CIRCLE && col2.shape == Shape.CIRCLE){
            CircleCollider cir1 = (CircleCollider)col1;
            CircleCollider cir2 = (CircleCollider)col2;

            //collision between circles is when the difference in their positions is less than their radii
            Vector2 relativePos = Vector2.difference(cir2.getPos(), cir1.getPos());
            if(relativePos.magnitude() < cir1.radius()+cir2.radius()){
                cir1.onCollisionEnter(cir2);
                cir2.onCollisionEnter(cir1);
            }
        }
    }

    public static MyArrayList<Collider> colliderList(){
        return allColliders;
    }
}
enum Shape{
    BOX,
    CIRCLE
}
