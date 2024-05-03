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
        }else{
            dummies.get(clientID).moveToPosition(pos);
        }
    }

    public void drawMe(Graphics g){
        for(int each : dummies.keySet().toDLList()){
            dummies.get(each).drawMe(g);
        }
    }
}
