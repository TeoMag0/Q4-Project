
/*
 * potential spawn points for players
 */

public class PlayerSpawns {
    private static final Vector2[] spawnPoints = new Vector2[]{
        new Vector2(-5, -18), //cell 1
        new Vector2(4, -18), //cell 2
        new Vector2(-5, -23), //cell 3
        new Vector2(4, -24), //cell 4
    };
    private static int spawnIndex;

    public static void movePlayerToSpawn(){
        Screen.player.setPos(TileMap.Singleton.rcToCoords(spawnPoints[spawnIndex]));
    }
    public static void setSpawnIndex(int index){
        spawnIndex = index;
    }
}
