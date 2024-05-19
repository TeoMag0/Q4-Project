
/*
 * threads attack
 */

public class BossDeathLinesAttack extends BossAttack{
    
    private float projectileSpeed;
    private float firerate;
    private float size;
    private Direction attackDir;
    
    public enum Direction {
        TOP,
        LEFT,
        RIGHT,
    }

    private final Vector2[] fromTopSpawnPointsRC = new Vector2[]{
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
    private final Vector2[] fromLeftSpawnPointsRC = new Vector2[]{
        new Vector2(-5, 7),
            new Vector2(-12, 4),
            new Vector2(-14, 1),
            new Vector2(-13, -2),
            new Vector2(-10, -5),
    };
    private final Vector2[] fromRightSpawnPointsRC = new Vector2[] {
            new Vector2(7, 6),
            new Vector2(12, 3),
            new Vector2(13, 0),
            new Vector2(12, -3),
            new Vector2(7, -6),
    };

    public BossDeathLinesAttack(Boss boss){
        super(boss);
        projectileSpeed = 3;
        firerate = 5;
        size = 0.4f;
    }


    public void run(){
        try{
            spawnProjectiles();
            Thread.sleep(1500);
            while(activeThread() != null){
                spawnProjectiles();
                Thread.sleep((int)(1000/firerate));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void spawnProjectiles(){
        switch(attackDir){
            case LEFT:
                for (Vector2 each : fromLeftSpawnPointsRC) {
                    Vector2 velocity = new Vector2(projectileSpeed, 0);
                    Vector2 spawnPoint = Vector2.sum(TileMap.Singleton.rcToCoords(each), new Vector2(TileMap.Singleton.tileSize() / 2, -TileMap.Singleton.tileSize() / 2));
                    BossProjectile.createProjectile("SidewaysOr.png", spawnPoint, size, velocity, true, false);
                }
                break;
            case RIGHT:
                for (Vector2 each : fromRightSpawnPointsRC) {
                    Vector2 velocity = new Vector2(-projectileSpeed, 0);
                    Vector2 spawnPoint = Vector2.sum(TileMap.Singleton.rcToCoords(each), new Vector2(TileMap.Singleton.tileSize() / 2, -TileMap.Singleton.tileSize() / 2));
                    BossProjectile.createProjectile("SidewaysOr.png", spawnPoint, size, velocity, true, false);
                }
                break;
            case TOP:
                for (Vector2 each : fromTopSpawnPointsRC) {
                    Vector2 velocity = new Vector2(0, -projectileSpeed);
                    Vector2 spawnPoint = Vector2.sum(TileMap.Singleton.rcToCoords(each), new Vector2(TileMap.Singleton.tileSize() / 2, -TileMap.Singleton.tileSize() / 2));
                    BossProjectile.createProjectile("OrStatement.png", spawnPoint, size, velocity, true, false);
                }
                break;
        }
    }
    public void setDirection(Direction dir){
        attackDir = dir;
    }
}

