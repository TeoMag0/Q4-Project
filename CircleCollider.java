public class CircleCollider extends Collider{
    private float radius;
    private Transform parent;
    
    public CircleCollider(Transform parent, float radius){
        super(parent);
        this.parent = parent;
        this.radius = radius;
    }

    public float radius(){
        return radius;
    }

    public void onCollisionEnter(Collider col){

    }
}
