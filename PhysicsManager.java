
/*
 * purpose: refresh colliders
 */

public class PhysicsManager extends Startable implements Runnable{
    public static final PhysicsManager Singleton = new PhysicsManager();
    private MyArrayList<Collider> colliders = new MyArrayList<>();
    public static final int physicsTimeStepMS = 7; //144 fps
    private static final float registerCollisionDistance = 5;

    public PhysicsManager(){
        colliders = Collider.colliderList();
    }
    public void start(){
        new Thread(this).start();
    }

    public synchronized void run(){
        while(true){

            Screen.player.movementManager.movePlayer(physicsTimeStepMS);

            Projectile.updateAll(physicsTimeStepMS);
            Collider.updateColliders();

            if(colliders.size() > 1){
                for(int i=0;i<colliders.size();i++){
                    for(int j=i+1;j<colliders.size();j++){
                        Collider c1 = colliders.get(i);
                        Collider c2 = colliders.get(j);
                        if(c1 == null || c2 == null){
                            continue;
                        }
                        if(c1.purpose() != c2.purpose() && Vector2.distance(c1.getPos(), c2.getPos()) < registerCollisionDistance){
                            Collider.checkCollision(c1, c2);
                        }
                    }
                }
            }

            Screen.Singleton.repaint();
            try{
                Thread.sleep(physicsTimeStepMS);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void wakeUp(){

    }
}
