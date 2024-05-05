
/*
 * purpose: Responsible for collision detection
 * to update: add new algorithms if new colliders are made
 */

public abstract class Collider implements Transform{
    private Shape shape;
    private HasCollider parent;
    private ColliderPurpose purpose;
    private static final MyArrayList<Collider> allColliders = new MyArrayList<>();

    public Collider(HasCollider parent, ColliderPurpose purpose, Shape shape){
        this.parent = parent;
        this.purpose = purpose;
        allColliders.add(this);
        this.shape = shape;
    }

    public ColliderPurpose purpose(){
        return purpose;
    }
    public void setPurpose(ColliderPurpose p){
        purpose = p;
    }

    public Shape shape(){
        return shape;
    }
    public Vector2 getPos(){
        return parent.getPos();
    }
    public HasCollider parent(){
        return parent;
    }

    public static void checkCollision(Collider col1, Collider col2){
        if(col1.shape() == Shape.CIRCLE && col2.shape == Shape.CIRCLE){
            CircleCollider cir1 = (CircleCollider)col1;
            CircleCollider cir2 = (CircleCollider)col2;

            //collision between circles is when the difference in their positions is less than their radii
            if(Vector2.distance(cir2.getPos(), cir1.getPos()) <= cir1.radius()+cir2.radius()){
                cir1.parent().onCollisionEnter(cir2);
                cir2.parent().onCollisionEnter(cir1);
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

            if(findBoxPointInCircle(box, cir) != null || findCirclePointInBox(cir, box) != null){
                box.parent().onCollisionEnter(cir);
                cir.parent().onCollisionEnter(box);
            }       
        }else if(col1.shape() == Shape.BOX && col2.shape() == Shape.BOX){
            BoxCollider box1 = (BoxCollider)col1;
            BoxCollider box2 = (BoxCollider)col2;

            Vector2 box1BottomLeft = box1.cornerBL();
            Vector2 box1TopRight = box1.cornerTR();
            Vector2 box2BottomLeft = box2.cornerBL();
            Vector2 box2TopRight = box2.cornerTR();

            if(box1BottomLeft.getX()<box2TopRight.getX() && box1TopRight.getX()>box2BottomLeft.getX() && box1BottomLeft.getY()<box2TopRight.getY() && box1TopRight.getY()>box2BottomLeft.getY()){
                box1.parent().onCollisionEnter(box2);
                box2.parent().onCollisionEnter(box1);
            }
        }
    }

    public static MyArrayList<Collider> colliderList(){
        return allColliders;
    }

    public static Vector2 findBoxPointInCircle(BoxCollider box, CircleCollider circle){
        for(Vector2 corner : box.corners()){
            if(Vector2.distance(corner, circle.getPos()) < circle.radius()){
                return corner;
            }
        }
        return null;
    }
    public static Vector2 findCirclePointInBox(CircleCollider circle, BoxCollider box){
        if(circle.getPos().getX() > box.cornerBL().getX() && circle.getPos().getX() < box.cornerTR().getX() && Math.abs(circle.getPos().getY()-box.getPos().getY()) <= (box.size().getY()/2+circle.radius())){
            if(circle.getPos().getY() > box.getPos().getY()){
                //circle on top of box
                return Vector2.sum(circle.getPos(), Vector2.multiply(Vector2.down(), circle.radius()));
            }else{
                //circle under box
                return Vector2.sum(circle.getPos(), Vector2.multiply(Vector2.up(), circle.radius()));
            }
        }else if(circle.getPos().getY() > box.cornerBL().getY() && circle.getPos().getY() < box.cornerTR().getY() && Math.abs(circle.getPos().getX()-box.getPos().getX()) <= (box.size().getX()/2+circle.radius())){
            if (circle.getPos().getX() > box.getPos().getX()) {
                // circle on right side
                return Vector2.sum(circle.getPos(), Vector2.multiply(Vector2.left(), circle.radius()));
            } else {
                // circle on left side
                return Vector2.sum(circle.getPos(), Vector2.multiply(Vector2.right(), circle.radius()));
            }
        }
        return null;
    }
}
enum Shape{
    BOX,
    CIRCLE
}
enum ColliderPurpose{
    PLAYER,
    WALL,
    ENEMYPROJECTILE,
    PLAYERPROJECTILE,
    DUD
}
