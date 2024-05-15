
/*
 * purpose: deals with all transitions of game states on the client side
 */

public class ClientGameManager {
    public static final ClientGameManager Singleton = new ClientGameManager();
    private GameState gameState;

    public ClientGameManager(){

    }
    public GameState gameState(){
        return gameState;
    }

    public void changeState(GameState newState){
        gameState = newState;
        switch(newState){
            case WAITING_FOR_PLAYERS:
                PlayerSpawns.movePlayerToSpawn();
                break;
            case GET_IN_ROOM:
                CellDoors.openCellDoors();
                Screen.player.uiManager.waitingText().fadeOut();
                break;
            case PHASE_1:
                Entry.closeEntry();
                Screen.boss.healthManager.drawHealth.setActive(true);
                break;
            case PHASE_2:
                break;
            case GAME_END:
                Entry.openEntry();
                Screen.boss.healthManager.drawHealth.setActive(false);
                break;
        }
    }
}
