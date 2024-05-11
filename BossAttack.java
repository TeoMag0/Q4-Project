public abstract class BossAttack implements Runnable{
    private Boss boss;
    private Thread activeThread;

    public BossAttack(Boss boss){
        this.boss = boss;
    }

    public Boss boss(){
        return boss;
    }
    public Thread activeThread(){
        return activeThread;
    }

    public void setActive(boolean active){
        if (active && activeThread == null) {
            activeThread = new Thread(this);
            activeThread.start();
        } else if (!active && activeThread != null) {
            activeThread = null;
        }
    }
}
