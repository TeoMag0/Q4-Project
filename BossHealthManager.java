import java.awt.*;

public class BossHealthManager extends Startable implements HasCollider, Runnable{
    
    private Boss boss;
    private Collider collider;
    private int maxHealth;
    private int health;
    private float hitSendInterval;
    private int damageToSend;
    private Thread activeThread;
    public final DrawBossHealth drawHealth;

    public BossHealthManager(Boss boss){
        this.boss = boss;
        hitSendInterval = 0.3f;
        drawHealth = new DrawBossHealth(this);

        collider = new BoxCollider(this, boss.size(), ColliderPurpose.BOSS);
    }
    public void start(){
        activeThread = new Thread(this);
        activeThread.start();
    }

    public void hit(){
        health--;
        damageToSend++;
    }

    public Vector2 getPos(){
        return boss.getPos();
    }

    public void onCollisionEnter(Collider col){
        if(col.purpose() == ColliderPurpose.PLAYER_PROJECTILE){
            hit();
            col.setPurpose(ColliderPurpose.DUD);
        }
    }

    public int health(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public void run(){
        try{
            while(true){
                if(damageToSend != 0){
                    ConnectionManager.Singleton.sendObject(new NetworkObject<Integer>(damageToSend, Packet.DAMAGE_TO_BOSS));
                    damageToSend = 0;
                }
                Thread.sleep((int)(hitSendInterval*1000));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void drawHealth(Graphics g){
        drawHealth.drawBossHealth(g);
    }

    public int maxHealth(){
        return maxHealth;
    }
    public void setMaxHealth(int health){
        maxHealth = health;
    }
}
