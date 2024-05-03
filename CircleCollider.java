public abstract class CircleCollider extends Collider{
    private float radius;
    
    public CircleCollider(Transform parent, float radius){
        super(parent);
        this.radius = radius;
    }

    public float radius(){
        return radius;
    }
}
