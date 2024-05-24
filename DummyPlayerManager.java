import java.awt.*;
/*
 * purpose: manage all things to do with other players being drawn on this screen
 */

public class DummyPlayerManager implements DrawableObject{
    private MyHashTable<Integer, DummyPlayer> dummies;
    public static final DummyPlayerManager Singleton = new DummyPlayerManager();

    public DummyPlayerManager(){
        dummies = new MyHashTable<>(10);
    }

    public void updatePosition(int clientID, Vector2 pos){
        if(dummies.get(clientID) == null){
            dummies.put(clientID, new DummyPlayer(pos));
            dummies.get(clientID).setColor(clientID);
        }else{
            dummies.get(clientID).moveToPosition(pos);
        }
    }

    public void setAlive(int clientID, boolean alive){
        if(dummies.get(clientID) == null){
            dummies.put(clientID, new DummyPlayer(Vector2.zero()));
            dummies.get(clientID).setColor(clientID);
        }
        dummies.get(clientID).setAlive(alive);
    }

    public void drawMe(Graphics g){
        for(int each : dummies.keySet().toDLList()){
            dummies.get(each).drawMe(g);
        }
    }

    public void launchProjectile(int clientID, Vector2 velocity){
        Vector2 pos = dummies.get(clientID).getPos();
        new DummyProjectile(pos, velocity);
    }

    public void remove(int clientID){
        dummies.remove(clientID);
    }
}
