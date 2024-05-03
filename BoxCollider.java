public class BoxCollider extends Collider{
    private Vector2 size;

    public BoxCollider(Transform parent, Vector2 size){
        super(parent);
        this.size = size.clone();
    }

    public Vector2 size(){
        return size.clone();
    }

    public void onCollisionEnter(Collider col){
        
    }
}
