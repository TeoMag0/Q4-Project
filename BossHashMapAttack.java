public class BossHashMapAttack extends BossAttack{
    private Vector2[] locations;
    private DLList<BossProjectile> activeProjectiles;
    private float size;

    public BossHashMapAttack(Boss boss, float size){
        super(boss);
        this.size = size;
        activeProjectiles = new DLList<>();
    }

    public void run(){
        try{
            for(int i=0;i<3;i++){
                createTargets(.5f);
                Thread.sleep(700);
            }
            createTargets(.2f);
            Thread.sleep(200);
            createProjectiles();
            Thread.sleep(5000);
            deleteProjectiles();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void createProjectiles(){
        for(Vector2 each : locations){
            BossProjectile projectile = BossProjectile.createProjectile("HMSprite.png", each, size, Vector2.zero(), true, true);
            projectile.wallImpervious(true);
            activeProjectiles.add(projectile);
        }
    }
    private void createTargets(float animTime){
        for(Vector2 each : locations){
            new HashmapTarget(each, size, animTime);
        }
    }
    private void deleteProjectiles(){
        while(activeProjectiles.size() != 0){
            activeProjectiles.get(0).destroySelf();
            activeProjectiles.remove(0);
        }
    }

    public void setPlayerPositions(Vector2[] pos){
        locations = pos.clone();
    }
}
