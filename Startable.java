
/*
 * purpose: keep track of all objects that have a start method
 */

public abstract class Startable {

    public static final MyArrayList<Startable> startables = new MyArrayList<>();
    
    public Startable(){
        startables.add(this);
    }
    public Startable(boolean isStartManager){
        if(!isStartManager){
            startables.add(this);
        }
    }
    public abstract void start();
}
