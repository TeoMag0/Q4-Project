
/*
 * purpose: Responsible for all interactions between colliders
 * to update: add new algorithms if new colliders are made
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
    public Transform parent(){
        return parent;
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
        }else if((col1.shape() == Shape.BOX && col2.shape() == Shape.CIRCLE) || (col2.shape() == Shape.BOX && col1.shape() == Shape.CIRCLE)){
            BoxCollider box;
            CircleCollider cir;
            if(col1.shape() == Shape.BOX){
                box = (BoxCollider)col1;
                cir = (CircleCollider)col2;
            }else{
                box = (BoxCollider)col2;
                cir = (CircleCollider)col1;
            }

            Vector2 closestCirclePoint = Vector2.multiply(Vector2.difference(box.getPos(), cir.getPos()).normalized(), cir.radius());
            Vector2 boxBottomLeft = Vector2.difference(box.getPos(), Vector2.multiply(box.size(), 1f/2));
            Vector2 boxTopRight = Vector2.sum(box.getPos(), Vector2.multiply(box.size(), 1f/2));
            //check if circle point is in box
            if(closestCirclePoint.getX()>boxBottomLeft.getX() && closestCirclePoint.getX()<boxTopRight.getX() && closestCirclePoint.getY()>boxBottomLeft.getY() && closestCirclePoint.getY()<boxTopRight.getY()){
                box.onCollisionEnter(cir);
                cir.onCollisionEnter(box);
            }
        }else if(col1.shape() == Shape.BOX && col2.shape() == Shape.CIRCLE){
            BoxCollider box1 = (BoxCollider)col1;
            BoxCollider box2 = (BoxCollider)col2;

            Vector2 box1BottomLeft = Vector2.difference(box1.getPos(), Vector2.multiply(box1.size(), 1f/2));
            Vector2 box1TopRight = Vector2.sum(box1.getPos(), Vector2.multiply(box1.size(), 1f/2));
            Vector2 box2BottomLeft = Vector2.difference(box2.getPos(), Vector2.multiply(box2.size(), 1f/2));
            Vector2 box2TopRight = Vector2.sum(box2.getPos(), Vector2.multiply(box2.size(), 1f/2));

            if(box1BottomLeft.getX()<box2TopRight.getX() && box1TopRight.getX()>box2BottomLeft.getX() && box1BottomLeft.getY()<box2TopRight.getY() && box1TopRight.getY()>box2BottomLeft.getY()){
                box1.onCollisionEnter(box2);
                box2.onCollisionEnter(box1);
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
