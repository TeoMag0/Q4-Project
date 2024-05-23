import java.io.Serializable;
import java.awt.*;

/*
 * purpose: Responsible for collision detection
 * to update: add new algorithms if new colliders are made
 */

public abstract class Collider implements Transform, Serializable, DrawableObject{
    private Shape shape;
    private HasCollider parent;
    private ColliderPurpose purpose;
    private static final MyArrayList<Collider> allColliders = new MyArrayList<>();
    private static final MyHashSet<Collider> collidersToRemove = new MyHashSet<>(30);
    private static final DLList<Collider> collidersToAdd = new DLList<>();

    public Collider(HasCollider parent, ColliderPurpose purpose, Shape shape){
        this.parent = parent;
        this.purpose = purpose;
        collidersToAdd.add(this);
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
    public static void removeCollider(Collider c){
        collidersToRemove.add(c);
    }
    public synchronized static void updateColliders(){
        while(collidersToAdd.size() != 0){
            Collider c = collidersToAdd.remove(0);
            allColliders.add(c);
        }
        while(collidersToRemove.toDLList().size() != 0){
            Collider c = collidersToRemove.toDLList().get(0);
            if(c == null){
                System.out.println("collider to remove is already null");
            }
            allColliders.remove(c);
            collidersToRemove.remove(c);
        }
    }

    public static boolean checkCollision(Collider col1, Collider col2){
        if(col1 == null || col2 == null){
            return false;
        }
        if(col1.purpose == ColliderPurpose.WALL && col2.purpose == ColliderPurpose.WALL){
            return false;
        }
        
        if(col1.shape() == Shape.CIRCLE && col2.shape == Shape.CIRCLE){
            CircleCollider cir1 = (CircleCollider)col1;
            CircleCollider cir2 = (CircleCollider)col2;

            //collision between circles is when the difference in their positions is less than their radii
            if(Vector2.distance(cir2.getPos(), cir1.getPos()) <= cir1.radius()+cir2.radius()){
                cir1.parent().onCollisionEnter(cir2);
                cir2.parent().onCollisionEnter(cir1);
                return true;
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
                return true;
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
                return true;
            }
        }
        return false;
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

    public static void drawAll(Graphics g){
        for(Collider each : allColliders){
            if(each != null){
                each.drawMe(g);
            }
        }
    }
}
enum Shape{
    BOX,
    CIRCLE
}
enum ColliderPurpose{
    PLAYER,
    WALL,
    ENEMY_PROJECTILE,
    PLAYER_PROJECTILE,
    DUD,
    BOSS,
    INTERACTABLE,
}
