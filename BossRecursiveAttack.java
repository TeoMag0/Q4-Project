public class BossRecursiveAttack extends BossAttack{

    public BossRecursiveAttack(Boss boss){
        super(boss);
    }

    public void run(){
        BossProjectileRecursive.createProjectile("Asterisk1.png", boss().getPos(), .7f, new Vector2(0, -2), 1f, 3);
    }
}
