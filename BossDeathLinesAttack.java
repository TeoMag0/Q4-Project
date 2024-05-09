
/*
 * threads attack
 */

public class BossDeathLinesAttack implements Runnable{
    
    private Boss boss;
    private Thread activeThread;
    private float projectileSpeed;
    private float firerate;
    private float size;

    private final Vector2[] projectileSpawnPointsRC = new Vector2[]{
        new Vector2(-2, 7),
        new Vector2(1, 7),
        new Vector2(4, 7),
        new Vector2(-5, 7),
        new Vector2(-8, 6),
        new Vector2(7, 6),
        new Vector2(10, 4),
        new Vector2(13, 1),
        new Vector2(-11, 4),
        new Vector2(-14, 1),
    };

    public BossDeathLinesAttack(Boss boss){
        this.boss = boss;
        projectileSpeed = 3;
        firerate = 5;
        size = 0.4f;
    }


    public void run(){
        try{
            spawnProjectiles();
            Thread.sleep(1500);
            while(activeThread != null){
                spawnProjectiles();
                Thread.sleep((int)(1000/firerate));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void spawnProjectiles(){
        for(Vector2 each : projectileSpawnPointsRC){
            Vector2 velocity = new Vector2(0, -projectileSpeed);
            Vector2 spawnPoint = Vector2.sum(TileMap.Singleton.rcToCoords(each), new Vector2(TileMap.Singleton.tileSize()/2, -TileMap.Singleton.tileSize()/2));
            new BossProjectile("OrStatement.png", spawnPoint, size, velocity, true, false);
        }
    }

    public void setActive(boolean active){
        if(active && activeThread == null){
            activeThread = new Thread(this);
            activeThread.start();
        }else if(!active && activeThread != null){
            activeThread = null;
        }
    }
}
