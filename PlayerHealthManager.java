public class PlayerHealthManager {
    private Player player;
    private int health;
    public final int maxHealth;

    public PlayerHealthManager(Player player, int maxHealth){
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.player = player;
    }

    //when hit by boss's projectile
    public void hit(){
        health--;
        Sound.playSound("pencilOnGlass.wav");
        if(health == 0){
            player.die();
        }
    }
    public void resetHealth(){
        health = maxHealth;
    }
    public int health(){
        return health;
    }
    public void heal(int amount){
        health += amount;
        if(health > maxHealth){
            health = maxHealth;
        }
    }
}
