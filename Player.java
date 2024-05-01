public class Player {
    private Vector2 position;

    public Player(Vector2 position){
        this.position = position.clone();
    }

    public void sendPosition(){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Vector2>(position, Packet.PLAYERPOS));
    }
}
