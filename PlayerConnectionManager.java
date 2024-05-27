
/*
 * purpose: manage anything that player needs to send to server
 */

public class PlayerConnectionManager implements Runnable{
    private Player player;
    private boolean alive;
    public static final int sendInterval = 100;//millisecond interval between position updates to the server

    public PlayerConnectionManager(Player player){
        this.player = player;
        alive = true;
    }
    
    public void run(){
        while(true){
            if(alive){
                ConnectionManager.Singleton.sendObject(new NetworkObject<Vector2>(player.getPos(), Packet.PLAYER_POS));//vector2 is 186 bytes, float[] is 177 bytes
            }
            try{
                Thread.sleep(sendInterval);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void die(){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Boolean>(false, Packet.PLAYER_STATUS));
        alive = false;
    }
    public void resurrect(){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Boolean>(true, Packet.PLAYER_STATUS));
        alive = true;
    }
    public void isInBossRoom(boolean is){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Boolean>(is, Packet.IS_IN_BOSS_ROOM));
    }

    public void sendProjectile(Vector2 velocity){
        ConnectionManager.Singleton.sendObject(new NetworkObject<Vector2>(velocity, Packet.PLAYER_PROJECTILE));
    }
}
