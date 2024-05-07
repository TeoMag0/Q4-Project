
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
    
    public Game(Manager manager){
        this.manager = manager;
        clients = new MyHashTable<>(10);
        MaxPlayers = 4;
    }

    @SuppressWarnings("rawtypes")
    public void update(int clientID, NetworkObject obj){
        switch(obj.packet){
            case PLAYERPOS:
                // receives Vector2 pos
                // sends {int clientID, Vector2 pos}
                manager.broadcastExcept(new NetworkObject<Object[]>(new Object[] { clientID, obj.data }, obj.packet), clientID);
                break;
            case PLAYERSTATUS:
                //recieves boolean alive
                //sends {int clientID, boolean alive}
                manager.broadcastExcept(new NetworkObject<Object[]>(new Object[] {clientID, obj.data}, obj.packet), clientID);
                break;
        }
    }
    @SuppressWarnings("rawtypes")
    public void updateGameState(NetworkObject obj){

    }

    public void addClient(int clientID){
        clients.put(clientID, new ClientInformation());
    }
    public void disconnectClient(int clientID){
        clients.remove(clientID);
    }
    public int numClients(){
        return clients.keySet().size();
    }
}
