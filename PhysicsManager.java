
/*
 * purpose: refresh colliders
 */

public class PhysicsManager extends Startable implements Runnable{
    public static final PhysicsManager Singleton = new PhysicsManager();
    private MyArrayList<Collider> colliders = new MyArrayList<>();
    public static final int physicsTimeStepMS = 10;

    public PhysicsManager(){
        colliders = Collider.colliderList();
    }
    public void start(){
        new Thread(this).start();
    }

    public void run(){
        while(true){

            Screen.player.movementManager.movePlayer();

            Projectile.updateAll(physicsTimeStepMS);

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
