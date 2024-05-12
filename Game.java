
/*
 * purpose: everything to be sent to/between clients goes through this guy it's like an FBI tap, also change game state
 */

public class Game {

    private Manager manager;
    private MyHashTable<Integer, ClientInformation> clients;
    public final int MaxPlayers;
    private GameState gameState;
    private GameSpawnIndexManager playerSpawnIndices;
    private GameBossAttackTiming bossTiming;
    private int bossHealth;
    private int bossMaxHealth;
    
    public Game(Manager manager){
        this.manager = manager;
        clients = new MyHashTable<>(10);
        MaxPlayers = 1;
        playerSpawnIndices = new GameSpawnIndexManager(MaxPlayers);
        gameState = GameState.WAITING_FOR_PLAYERS;
        bossTiming = new GameBossAttackTiming(manager, this);
    }

    @SuppressWarnings("rawtypes")
    public void update(int clientID, NetworkObject obj){
        switch(obj.packet){
            case PLAYER_POS:
                // receives Vector2 pos
                // sends {int clientID, Vector2 pos}
                manager.broadcastExcept(clientID, new NetworkObject<Object[]>(new Object[] { clientID, obj.data }, obj.packet));
                clients.get(clientID).setPos((Vector2)obj.data);
                break;
            case PLAYER_STATUS:
                //recieves boolean alive
                //sends {int clientID, boolean alive}
                manager.broadcastExcept(clientID, new NetworkObject<Object[]>(new Object[] {clientID, obj.data}, obj.packet));
                break;
            case IS_IN_BOSS_ROOM:
                //receive boolean inBossRoom
                if(gameState == GameState.GET_IN_ROOM){
                    clients.get(clientID).inBossRoom((boolean) obj.data);
                }
                checkIfClientsInBossRoom();
                break;
            case DAMAGE_TO_BOSS:
                //receive int damage
                bossHealth -= (int)obj.data;
                manager.broadcast(new NetworkObject<Integer>(bossHealth, Packet.BOSS_HEALTH));
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
                bossTiming.setActive(true);
                sendBossMaxHealth();
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
        gameState = next;
        manager.broadcast(new NetworkObject<GameState>(next, Packet.GAME_STATE_CHANGE));//broadcast state change
    }
    public void checkIfClientsInBossRoom(){
        boolean allIn = true;
        for(int each : clients.keySet().toDLList()){
            if(!clients.get(each).inBossRoom()){
                allIn = false;
            }
        }
        if(allIn){
            nextState();
        }
    }
    public void sendBossMaxHealth(){
        bossMaxHealth = 100*clients.keySet().toDLList().size();
        bossHealth = bossMaxHealth;
        manager.broadcast(new NetworkObject<Integer>(bossMaxHealth, Packet.BOSS_MAX_HEALTH));
        manager.broadcast(new NetworkObject<Integer>(bossHealth, Packet.BOSS_HEALTH));
    }
    public Vector2[] getPlayerPositions(){
        Vector2[] array = new Vector2[numClients()];
        int i=0;
        for(int each : clients.keySet().toDLList()){
            array[i] = clients.get(each).getPos();
            i++;
        }
        return array;
    }
}
