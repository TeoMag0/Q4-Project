
/*
 * purpose: manage all operations that need to be done after initialization but before any updates
 */

public class StartManager extends Startable{
    public static final StartManager Singleton = new StartManager();

    public StartManager(){
        super(true);
    }

    public void start(){
        for(Startable each : startables){
            each.start();
        }
    }
}