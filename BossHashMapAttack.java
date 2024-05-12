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
                createTargets();
                Thread.sleep(700);
            }
            createProjectiles();
            Thread.sleep(5000);
            deleteProjectiles();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void createProjectiles(){
        for(Vector2 each : locations){
            BossProjectile projectile = new BossProjectile("HMSprite.png", each, size, Vector2.zero(), true, true);
            projectile.wallImpervious(true);
            activeProjectiles.add(projectile);
        }
    }
    private void createTargets(){
        for(Vector2 each : locations){
            new HashmapTarget(each, size, 0.5f);
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
