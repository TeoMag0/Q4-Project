
/*
 * purpose: everything to be sent to/between clients goes through this guy it's like an FBI tap, also change game state
 */

public class Game {

    private Manager manager;
    private MyHashTable<Integer, ClientInformation> clients;
    public final int MaxPlayers;
    private GameState gameState;
    private GameSpawnIndexManager playerSpawnIndices;
    
    public Game(Manager manager){
        this.manager = manager;
        clients = new MyHashTable<>(10);
        MaxPlayers = 1;
        playerSpawnIndices = new GameSpawnIndexManager(MaxPlayers);
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
            default:
                break;
        }
    }

    public void addClient(int clientID){
        System.out.println(String.format("Client %s added", clientID));
        clients.put(clientID, new ClientInformation());
        playerSpawnIndices.addPlayer(clientID);
        manager.send(clientID, new NetworkObject<Integer>(playerSpawnIndices.spawnIndexOf(clientID), Packet.PLAYER_SPAWN_INDEX));//send spawn index
        manager.send(clientID, new NetworkObject<GameState>(gameState, Packet.GAME_STATE_CHANGE));//send current state
        if(gameState == GameState.WAITING_FOR_PLAYERS){
            manager.broadcast(new NetworkObject<int[]>(new int[] {numClients(), MaxPlayers}, Packet.WAITING_PLAYERS));//update waiting text
            if(numClients() == MaxPlayers){
                nextState();
            }
        }
    }
    public void disconnectClient(int clientID){
        System.out.println(String.format("Client %s disconnected", clientID));
        manager.remove(clientID);
        clients.remove(clientID);
        manager.broadcast(new NetworkObject<Integer>(clientID, Packet.DISCONNECTED_PLAYER));//tells client to delete dummy
        playerSpawnIndices.removePlayer(clientID);
        if (gameState == GameState.WAITING_FOR_PLAYERS) {
            manager.broadcast(new NetworkObject<int[]>(new int[] { numClients(), MaxPlayers }, Packet.WAITING_PLAYERS));//update waiting text
        }
    }
    public int numClients(){
        return clients.keySet().size();
    }

    public void nextState(){
        GameState next;
        switch (gameState) {
            case WAITING_FOR_PLAYERS:
                next = GameState.GET_IN_ROOM;
                break;
            case GET_IN_ROOM:
                next = GameState.PHASE_1;
                break;
            case PHASE_1:
                next = GameState.PHASE_2;
                break;
            case PHASE_2:
                next = GameState.GAME_END;
                break;
            default:
                next = GameState.WAITING_FOR_PLAYERS;
                break;
        }
        manager.broadcast(new NetworkObject<GameState>(next, Packet.GAME_STATE_CHANGE));//broadcast state change
    }
}
