
/*
 * purpose: refresh colliders
 */

public class ColliderManager extends Startable implements Runnable{
    public static final ColliderManager Singleton = new ColliderManager();
    private MyArrayList<Collider> colliders = new MyArrayList<>();
    public static final int physicsTimeStepMS = 10;

    public ColliderManager(){
        colliders = Collider.colliderList();
    }
    public void start(){
        new Thread(this).start();
    }

    public void run(){
        while(true){

            Screen.player.movementManager.movePlayer();

            if(colliders.size() > 1){
                for(int i=0;i<colliders.size();i++){
                    for(int j=i+1;j<colliders.size();j++){
                        Collider.checkCollision(colliders.get(i), colliders.get(j));
                    }
                }
            }

            Screen.Singleton.repaint();
            try{
                Thread.sleep(physicsTimeStepMS);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }

    public void wakeUp(){

    }
}
