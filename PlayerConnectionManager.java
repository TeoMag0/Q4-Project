public class PlayerConnectionManager implements Runnable{
    private Player player;
    private final int sendInterval;//millisecond interval between position updates to the server

    public PlayerConnectionManager(Player player){
        this.player = player;
        sendInterval = 100;
    }
    
    public void run(){
        while(true){
            player.sendPosition();
            try{
                Thread.sleep(sendInterval);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
