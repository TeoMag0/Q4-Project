
/*
 * purpose: provide collision detection in the shape of a rectangle
 */

public abstract class BoxCollider extends Collider{
    private Vector2 size;

    public BoxCollider(Transform parent, Vector2 size){
        super(parent);
        this.size = size.clone();
    }

    public Vector2 size(){
        return size.clone();
    }
    public Vector2 cornerTL(){
        return Vector2.difference(parent().getPos(), Vector2.multiply(new Vector2(-size.getY(), size.getX()), 1f / 2));
    }
    public Vector2 cornerTR(){
        return Vector2.sum(parent().getPos(), Vector2.multiply(size, 1f / 2));
    }
    public Vector2 cornerBR(){
        return Vector2.sum(parent().getPos(), Vector2.multiply(new Vector2(-size.getY(), size.getX()), 1f / 2));
    }
    public Vector2 cornerBL(){
        return Vector2.sum(parent().getPos(), Vector2.multiply(size, 1f / 2));
    }
    public Vector2[] corners(){
        return new Vector2[] {cornerTL(), cornerTR(), cornerBR(), cornerBL()};
    }
}
