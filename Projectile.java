import java.awt.*;

/*
 * abstract class for any kind of projectile
 */

public abstract class Projectile implements HasCollider, Transform, DrawableObject{
    public static final MyArrayList<Projectile> allProjectiles = new MyArrayList<>();
    private Vector2 pos, velocity;
    public static final MyHashSet<Projectile> projectilesToDelete = new MyHashSet<>(30);
    public static final DLList<Projectile> projectilesToAdd = new DLList<>();
    
    public Projectile(Vector2 position, Vector2 velocity){
        projectilesToAdd.add(this);
        this.pos = position;
        this.velocity = velocity;
    }

    public Vector2 getPos(){
        return pos.clone();
    }
    public Vector2 getVelocity(){
        return velocity.clone();
    }
    public void setVelocity(Vector2 velocity){
        this.velocity = velocity.clone();
    }
    public synchronized void update(float deltaTime){
        if(velocity == null){
            System.out.println(this+" has null velocity");
        }
        pos.add(Vector2.multiply(velocity, deltaTime));
    }
    public synchronized static void updateAll(int deltaTimeMS){
        while(projectilesToAdd.size() != 0){
            Projectile p = projectilesToAdd.remove(0);
            allProjectiles.add(p);
        }
        while(projectilesToDelete.toDLList().size() != 0){
            Projectile p = projectilesToDelete.toDLList().get(0);
            allProjectiles.remove(p);
            Collider.removeCollider(p.collider());
            projectilesToDelete.remove(p);
        }

        for(Projectile each : allProjectiles){
            if(each != null){
                each.update(1f * deltaTimeMS / 1000);
            }
        }
    }
    public static void drawAll(Graphics g){
        for(Projectile each : allProjectiles){
            if(each != null){
                each.drawMe(g);
            }
        }
    }
    public abstract Collider collider();

    public void destroySelf(){
        projectilesToDelete.add(this);
    }
}
