
/*
 * purpose: everything to be sent to/between clients goes through this guy it's like an FBI tap
 */

public class Game {

    private Manager manager;
    private MyHashTable<Integer, ClientInformation> clients;
    
    public Game(Manager manager){
        this.manager = manager;
        clients = new MyHashTable<>(10);
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

    public void addClient(int clientID){
        clients.put(clientID, new ClientInformation());
    }
}
