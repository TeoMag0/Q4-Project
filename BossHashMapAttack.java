public class BossHashMapAttack extends BossAttack{
    private Vector2[] locations;
    private DLList<BossProjectile> activeProjectiles;
    private float size;

    public BossHashMapAttack(Boss boss, float size){
        super(boss);
        this.size = size;
    }

    public void run(){
        try{
            for(int i=0;i<3;i++){
                createProjectiles();
                Thread.sleep(1000);
                deleteProjectiles();
                Thread.sleep(1000);
                createProjectiles();
                Thread.sleep(5000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void createProjectiles(){
        for(Vector2 each : locations){
            activeProjectiles.add(new BossProjectile("BlankSprite.png", each, size, Vector2.zero(), true, true));
        }
    }
    private void deleteProjectiles(){
        while(activeProjectiles.size() != 0){
            activeProjectiles.get(0).destroySelf();
        }
    }

    public void setPlayerPositions(Vector2[] pos){
        locations = pos.clone();
    }
}
