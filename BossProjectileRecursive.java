public class BossProjectileRecursive extends BossProjectile{
    
    private int iterations;
    private int spawns;
    private float lifetime;
    private float startAngle;

    public BossProjectileRecursive(String pic, Vector2 position, float size, Vector2 velocity, float lifetime, int iterations){
        super(pic, position, size, velocity, lifetime, true, true, true);
        this.iterations = iterations;
        spawns = 3;
        this.lifetime = lifetime;
        startAngle = (float)(Math.random()*2*Math.PI);
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(lifeLeft() <= 0 && iterations > 0){
            float angleInc = (float)(2*Math.PI/spawns);
            for(int i=0;i<spawns;i++){
                float angle = startAngle+i*angleInc;
                Vector2 velocity = new Vector2(getVelocity().magnitude(), angle, true);
                createProjectile("Asterisk1.png", getPos(), .3f, velocity, lifetime, iterations-1);
            }
        }
    }

    public static synchronized void createProjectile(String pic, Vector2 position, float size, Vector2 velocity, float lifetime, int iterations){
        new BossProjectileRecursive(pic, position, size, velocity, lifetime, iterations);
    }
}
