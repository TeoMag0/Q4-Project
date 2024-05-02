import java.util.*;

/*
 * purpose: facilitate communication between clients
 * things to update:
 */

public class Manager{
    private LinkedList<ServerThread> serverThreads;

    public Manager(){
        serverThreads = new LinkedList<ServerThread>();
    }

    public void add(ServerThread serverThread) {
        serverThreads.add(serverThread);
    }
    public void remove(ServerThread serverThread){
        serverThreads.remove(serverThread);
    }

    @SuppressWarnings("rawtypes")
    public void broadcast(NetworkObject message){
        for(ServerThread each : serverThreads){
            each.send(message);
        }
    }

    
}