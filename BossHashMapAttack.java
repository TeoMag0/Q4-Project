public class BossHashMapAttack extends BossAttack{
    private Vector2[] locations;
    private float size;

    public BossHashMapAttack(Boss boss, float size){
        super(boss);
        this.size = size;
    }

    public void run(){
        try{
            for(int i=0;i<3;i++){
                Sound.playSound("straw.wav");
                createTargets(.5f);
                Thread.sleep(700);
            }
            createTargets(.2f);
            Thread.sleep(200);
            Sound.playSound("straw.wav");
            createProjectiles();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void createProjectiles(){
        for(Vector2 each : locations){
            BossProjectile.createProjectile("HMSprite.png", each, size, Vector2.zero(), 5f, true, true, true);
        }
    }
    private void createTargets(float animTime){
        for(Vector2 each : locations){
            new HashmapTarget(each, size, animTime);
        }
    }

    public void setPlayerPositions(Vector2[] pos){
        locations = pos.clone();
    }
}
