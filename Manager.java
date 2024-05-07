
/*
 * purpose: facilitate communication between clients
 * things to update:
 */

public class Manager{
    private DLList<ServerThread> serverThreads;

    public Manager(){
        serverThreads = new DLList<ServerThread>();
    }

    public void add(ServerThread serverThread) {
        serverThreads.add(serverThread);
    }
    public void remove(ServerThread serverThread){
        serverThreads.remove(serverThread);
    }
    public void remove(int clientID){
        for(ServerThread each : serverThreads){
            if(each.clientID() == clientID){
                serverThreads.remove(each);
                return;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public void broadcast(NetworkObject message){
        for(ServerThread each : serverThreads){
            each.send(message);
        }
    }
    
    @SuppressWarnings("rawtypes")
    public void broadcastExcept(int clientID, NetworkObject message) {
        for (ServerThread each : serverThreads) {
            if(each.clientID() != clientID){
                each.send(message);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public void send(int clientID, NetworkObject message){
        for (ServerThread each : serverThreads) {
            if(each.clientID() == clientID){
                each.send(message);
                return;
            }
        }
    }
    
}