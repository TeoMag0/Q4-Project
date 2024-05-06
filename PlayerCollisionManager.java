
/*
 * purpose: deal with all of the player's collisions
 * to update: must be able to interact with all kinds of colliiders
 */

public class PlayerCollisionManager implements HasCollider, Transform{
    
    private Player player;
    private CircleCollider playerCol;
    private boolean active;

    public PlayerCollisionManager(Player player, float radius){
        playerCol = new CircleCollider(this, radius, ColliderPurpose.PLAYER);
        this.player = player;
        active = true;
    }

    public Vector2 getPos(){
        return player.getPos();
    }

    public void onCollisionEnter(Collider col){
        if(!active){
            return;
        }
        if(col.purpose() == ColliderPurpose.WALL){
            BoxCollider wallCol = (BoxCollider)col;
            // normal force direction on circle
            Vector2 normalForceDir = null;

            // check if circle is on a side, if not then check if on a corner
            Vector2 circleCol = Collider.findCirclePointInBox(playerCol, wallCol);
            Vector2 boxCol = Collider.findBoxPointInCircle(wallCol, playerCol);
            if (circleCol != null) {
                normalForceDir = Vector2.difference(getPos(), circleCol);
                if(Vector2.dot(normalForceDir, Vector2.right()) > 0){
                    //on the right side
                    player.moveTransform(Vector2.multiply(Vector2.right(), playerCol.radius()+wallCol.size().getX()/2 - Vector2.dot(Vector2.difference(getPos(),wallCol.getPos()), Vector2.right())));
                }else if(Vector2.dot(normalForceDir, Vector2.right()) < 0){
                    //on the left side
                    player.moveTransform(Vector2.multiply(Vector2.left(), playerCol.radius()+wallCol.size().getX()/2 - Vector2.dot(Vector2.difference(getPos(),wallCol.getPos()), Vector2.left())));
                }else if(Vector2.dot(normalForceDir, Vector2.up()) > 0){
                    //on top
                    player.moveTransform(Vector2.multiply(Vector2.up(), playerCol.radius()+wallCol.size().getY()/2 - Vector2.dot(Vector2.difference(getPos(),wallCol.getPos()), Vector2.up())));
                }else if(Vector2.dot(normalForceDir, Vector2.up()) < 0){
                    //on bottom
                    player.moveTransform(Vector2.multiply(Vector2.down(), playerCol.radius()+wallCol.size().getY()/2 - Vector2.dot(Vector2.difference(getPos(),wallCol.getPos()), Vector2.down())));
                }
            }else if(boxCol != null){
                normalForceDir = Vector2.difference(getPos(), boxCol);
            }
            if(normalForceDir != null){
                player.addRedirectionVector(normalForceDir.normalized());
            }
        }else if(col.purpose() == ColliderPurpose.ENEMYPROJECTILE){
            player.healthManager.hit();
            col.setPurpose(ColliderPurpose.DUD);
        }
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
