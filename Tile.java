import java.io.Serializable;

public class Tile implements HasCollider, Serializable{
    private Vector2 rc;
    private TilePic pic;
    private Collider collider;
    private float size;

    public Tile(Vector2 rc, TilePic pic, boolean isWall, float size){
        this.rc = rc.clone();
        this.pic = pic;
        this.size = size;
        if(isWall){
            collider = new BoxCollider(this, new Vector2(size, size), ColliderPurpose.WALL);
        }else{
            collider = null;
        }
    }

    public Vector2 getPos(){
        return Vector2.sum(TileMap.Singleton.rcToCoords(rc), new Vector2(size/2, -size/2));
    }
    public void setCR(Vector2 cr){
        this.rc = cr.clone();
    }
    public TilePic pic(){
        return pic;
    }

    public void onCollisionEnter(Collider col){

    }
}
