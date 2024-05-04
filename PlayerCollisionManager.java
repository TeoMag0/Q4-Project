
/*
 * purpose: deal with all of the player's collisions
 * to update: must be able to interact with all kinds of colliiders
 */

public class PlayerCollisionManager extends CircleCollider{
    

    public PlayerCollisionManager(Player player, float radius){
        super(player, radius);
    }

    public void onCollisionEnter(Collider col){
        if(col.getClass() == WallCollider.class){
            // normal force direction on circle
            WallCollider wall = (WallCollider) col;

            // check if circle is on a side, if not then check if on a corner
            Vector2 circleCol = findCirclePointInBox(this, wall);
            if (circleCol != null) {

            }
        }
    }
}
