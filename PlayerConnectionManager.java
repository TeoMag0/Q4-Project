public class PlayerConnectionManager implements Runnable{
    private Player player;
    public static final int sendInterval = 100;//millisecond interval between position updates to the server

    public PlayerConnectionManager(Player player){
        this.player = player;
    }
    
    public void run(){
        while(true){
            ConnectionManager.Singleton.sendObject(new NetworkObject<Vector2>(player.getPos(), Packet.PLAYERPOS));//vector2 is 186 bytes, float[] is 177 bytes
            try{
                Thread.sleep(sendInterval);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
