public class BossProjectileBouncy extends BossProjectile{
    
    private final int maxBounces;
    private int curBounce;

    public BossProjectileBouncy(String pic, Vector2 position, float size, Vector2 velocity, int maxBounces){
        super(pic, position, size, velocity, Float.POSITIVE_INFINITY, true, true, true, Shape.CIRCLE);
        this.maxBounces = maxBounces;
        curBounce = 0;
    }

    @Override
    public void onCollisionEnter(Collider col){
        super.onCollisionEnter(col);
        if(col.purpose() == ColliderPurpose.WALL){

            curBounce++;
            if (curBounce == maxBounces) {
                destroySelf();
            }

            BoxCollider wallCol = (BoxCollider) col;

            Vector2 normalForceDir = null;

            Vector2 circleCol = Collider.findCirclePointInBox((CircleCollider)collider(), wallCol);
            Vector2 boxCol = Collider.findBoxPointInCircle(wallCol, (CircleCollider)collider());
            if (circleCol != null) {
                normalForceDir = Vector2.difference(getPos(), circleCol).normalized();
            } else if (boxCol != null) {
                normalForceDir = Vector2.difference(getPos(), boxCol).normalized();
            }

            float vPerp = Vector2.dot(getVelocity(), normalForceDir);
            Vector2 deltaV = Vector2.multiply(normalForceDir, -2*vPerp);

            setVelocity(Vector2.sum(getVelocity(), deltaV));
        }
    }
}
