
/*
 * manage delegation of spawn points to players
 */

public class GameSpawnIndexManager {
    private int[] spawnIndices;

    public GameSpawnIndexManager(int numSpawns){
        spawnIndices = new int[numSpawns];
        for(int i=0;i<spawnIndices.length;i++){
            spawnIndices[i] = -1;
        }
    }

    public void addPlayer(int clientID){
        for(int i=0;i<spawnIndices.length;i++){
            if(spawnIndices[i] == -1){
                spawnIndices[i] = clientID;
                return;
            }
        }
    }
    public void removePlayer(int clientID){
        for(int i=0;i<spawnIndices.length;i++){
            if(spawnIndices[i] == clientID){
                spawnIndices[i] = -1;
                return;
            }
        }
    }
    public int spawnIndexOf(int clientID){
        for(int i=0;i<spawnIndices.length;i++){
            if(spawnIndices[i] == clientID){
                return i;
            }
        }
        return -1;
    }
}
