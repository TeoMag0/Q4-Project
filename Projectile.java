import java.awt.*;

/*
 * abstract class for any kind of projectile
 */

public abstract class Projectile implements HasCollider, Transform, DrawableObject{
    public static final MyArrayList<Projectile> allProjectiles = new MyArrayList<>();
    private Vector2 pos, velocity;
    public static final DLList<Projectile> projectilesToDelete = new DLList<>();
    
    public Projectile(Vector2 position, Vector2 velocity){
        allProjectiles.add(this);
        this.pos = position;
        this.velocity = velocity;
    }

    public Vector2 getPos(){
        return pos.clone();
    }
    public Vector2 getVelocity(){
        return velocity.clone();
    }
    public void update(float deltaTime){
        pos.add(Vector2.multiply(velocity, deltaTime));
    }
    public static void updateAll(int deltaTimeMS){
        while(projectilesToDelete.size() != 0){
            Projectile p = projectilesToDelete.get(0);
            allProjectiles.remove(p);
            Collider.colliderList().remove(p.collider());
        }

        for(Projectile each : allProjectiles){
            each.update(1f*deltaTimeMS/1000);
        }
    }
    public static void drawAll(Graphics g){
        for(Projectile each : allProjectiles){
            each.drawMe(g);
        }
    }
    public abstract Collider collider();

    public void destroySelf(){
        projectilesToDelete.add(this);
    }
}
