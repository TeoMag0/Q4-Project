
/*
 * purpose: refresh colliders
 */

public class ColliderManager implements Runnable{
    public static final ColliderManager Singleton = new ColliderManager();
    private MyArrayList<Collider> colliders = new MyArrayList<>();
    public static final int physicsTimeStepMS = 10;

    public ColliderManager(){
        colliders = Collider.colliderList();
    }

    public void run(){
        while(true){

            if(colliders.size() > 1){
                for(int i=0;i<colliders.size();i++){
                    for(int j=i+1;j<colliders.size();j++){
                        Collider.checkCollision(colliders.get(i), colliders.get(j));
                    }
                }
            }

            try{
                Thread.sleep(physicsTimeStepMS);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
