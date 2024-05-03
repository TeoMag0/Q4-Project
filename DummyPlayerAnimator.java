
/*
 * purpose: deal with any animation of dummy players
 */

public class DummyPlayerAnimator implements Runnable {
   
    private Vector2[] dummyLocs;//acts like a queue
    private int receivingInterval;
    private int deltaTimeMS;
    private Vector2 deltaPos;
    private DummyPlayer player;

    public DummyPlayerAnimator(DummyPlayer player){
        dummyLocs = new Vector2[2];
        dummyLocs[0] = player.getPos();
        receivingInterval = PlayerConnectionManager.sendInterval;
        deltaTimeMS = 10;
        deltaPos = Vector2.zero();
        this.player = player;
    }

    public void addNewDestination(Vector2 destination){
        //dummyLocs = {prevpos, destination}
        if(dummyLocs[1] != null){
            dummyLocs[0] = dummyLocs[1];
        }
        player.setPosition(dummyLocs[0]);
        dummyLocs[1] = destination.clone();
        deltaPos = Vector2.multiply(Vector2.difference(dummyLocs[1], dummyLocs[0]), 1f*deltaTimeMS/receivingInterval);
    }

    public void run(){
        while(true){

            if(!deltaPos.equals(Vector2.zero())){
                player.movePosition(deltaPos);
            }

            Screen.Singleton.repaint();
            try{
                Thread.sleep(deltaTimeMS);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
