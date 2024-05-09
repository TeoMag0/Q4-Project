public class GameBossAttackTiming implements Runnable{
    
    private Thread activeThread = null;
    private float attackDuration;

    public GameBossAttackTiming(){
        attackDuration = 10;
    }

    public void run(){
        try{
            while(activeThread != null){
                pickRandomAttack();
                Thread.sleep((int)(1000/attackDuration));
                stopAttack();
                Thread.sleep(3000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void pickRandomAttack(){

    }

    private void stopAttack(){

    }

    public void setActive(boolean active){
        if(active && activeThread == null){
            activeThread = new Thread(this);
            activeThread.start();
        }else if(!active && activeThread != null){
            activeThread = null;
        }
    }
}
