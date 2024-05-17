public class BossProjectileRecursiveAttack extends BossProjectile{
    
    private int iterations;
    private int spawns;
    private float lifetime;

    public BossProjectileRecursiveAttack(String pic, Vector2 position, float size, Vector2 velocity, float lifetime, int iterations){
        super(pic, position, size, velocity, lifetime, true, true, true);
        this.iterations = iterations;
        spawns = 3;
        this.lifetime = lifetime;
    }

    @Override
    public void update(float deltaTime){
        if(lifeLeft() <= 0 && iterations > 0){
            float angleInc = (float)(2*Math.PI/spawns);
            for(int i=0;i<spawns;i++){
                float angle = i*angleInc;
                Vector2 velocity = new Vector2(getVelocity().magnitude(), angle, true);
                BossProjectileRecursiveAttack.createProjectile("BlankSprite.png", getPos(), .3f, velocity, lifetime, iterations-1);
            }
        }
    }

    public static synchronized void createProjectile(String pic, Vector2 position, float size, Vector2 velocity, float lifetime, int iterations){
        new BossProjectileRecursiveAttack(pic, position, size, velocity, lifetime, iterations);
    }
}
