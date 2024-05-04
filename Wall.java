import java.awt.*;

/*
 * a single wall object, drawing and collision
 */

public class Wall implements HasCollider, Transform, DrawableObject{

    private Vector2 position;
    private Vector2 size;
    public static final MyArrayList<Wall> allWalls = new MyArrayList<>();
    private BoxCollider collider;

    public Wall(Vector2 position, Vector2 size){
        allWalls.add(this);
        this.position = position;
        this.size = size;
        collider = new BoxCollider(this, size, ColliderPurpose.WALL);
    }

    public void onCollisionEnter(Collider col){
        //does nothing on collision, it's a wall :p
    }
    public Vector2 getPos(){
        return position;
    }
    public void drawMe(Graphics g){
        //put pictures in later I guess?
        Vector2 screenLoc = Screen.getScreenCoords(collider.cornerTL());
        g.fillRect(screenLoc.intX(), screenLoc.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()));
    }
}
