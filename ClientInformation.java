
/*
 * purpose: contains all information about a client that the server needs to keep
 */

public class ClientInformation{
    private Vector2 position;
    private boolean isAlive;
    private boolean inBossRoom;

    public void setPos(Vector2 pos){
        position = pos.clone();
    }
    public Vector2 getPos(){
        return position.clone();
    }
    public void inBossRoom(boolean in){
        inBossRoom = in;
    }
    public boolean inBossRoom(){
        return inBossRoom;
    }

}