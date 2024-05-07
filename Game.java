
/*
 * purpose: everything to be sent to/between clients goes through this guy it's like an FBI tap, also change game state
 */

 enum GameState {
    WAITING_FOR_PLAYERS,
    PHASE_1,
    PHASE_2,
    GAME_END
}

public class Game {

    private Manager manager;
    private MyHashTable<Integer, ClientInformation> clients;
    public final int MaxPlayers;
    private GameState gameState;
    
    public Game(Manager manager){
        this.manager = manager;
        clients = new MyHashTable<>(10);
        MaxPlayers = 4;
        gameState = GameState.WAITING_FOR_PLAYERS;
    }

    @SuppressWarnings("rawtypes")
    public void update(int clientID, NetworkObject obj){
        switch(obj.packet){
            case PLAYER_POS:
                // receives Vector2 pos
                // sends {int clientID, Vector2 pos}
                manager.broadcastExcept(clientID, new NetworkObject<Object[]>(new Object[] { clientID, obj.data }, obj.packet));
                break;
            case PLAYER_STATUS:
                //recieves boolean alive
                //sends {int clientID, boolean alive}
                manager.broadcastExcept(clientID, new NetworkObject<Object[]>(new Object[] {clientID, obj.data}, obj.packet));
                break;
        }
    }

    public void addClient(int clientID){
        clients.put(clientID, new ClientInformation());
        if(gameState == GameState.WAITING_FOR_PLAYERS){
            manager.broadcast(new NetworkObject<int[]>(new int[] {numClients(), MaxPlayers}, Packet.WAITING_PLAYERS));
        }
    }
    public void disconnectClient(int clientID){
        clients.remove(clientID);
        if (gameState == GameState.WAITING_FOR_PLAYERS) {
            manager.broadcast(new NetworkObject<int[]>(new int[] { numClients(), MaxPlayers }, Packet.WAITING_PLAYERS));
        }
    }
    public int numClients(){
        return clients.keySet().size();
    }
}
