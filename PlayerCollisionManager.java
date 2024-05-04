
/*
 * purpose: deal with all of the player's collisions
 * to update: must be able to interact with all kinds of colliiders
 */

public class PlayerCollisionManager implements HasCollider, Transform{
    
    private Player player;
    private CircleCollider playerCol;

    public PlayerCollisionManager(Player player, float radius){
        playerCol = new CircleCollider(this, radius, ColliderPurpose.PLAYER);
        this.player = player;
    }

    public Vector2 getPos(){
        return player.getPos();
    }

    public void onCollisionEnter(Collider col){
        if(col.purpose() == ColliderPurpose.WALL){
            BoxCollider wallCol = (BoxCollider)col;
            // normal force direction on circle
            Vector2 normalForceDir = null;

            // check if circle is on a side, if not then check if on a corner
            Vector2 circleCol = Collider.findCirclePointInBox(playerCol, wallCol);
            Vector2 boxCol = Collider.findBoxPointInCircle(wallCol, playerCol);
            if (circleCol != null) {
                normalForceDir = Vector2.difference(getPos(), circleCol);
            }else if(boxCol != null){
                normalForceDir = Vector2.difference(getPos(), boxCol);
            }
            if(normalForceDir != null){
                player.setRedirectionVector(normalForceDir.normalized());
            }
        }
    }
}
