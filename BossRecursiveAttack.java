public class BossRecursiveAttack extends BossAttack{

    public BossRecursiveAttack(Boss boss){
        super(boss);
    }

    public void run(){
        BossProjectileRecursive.createProjectile("Asterisk1.png", boss().getPos(), 1f, new Vector2(0, -2), 1.5f, 3);
    }
}
