public class GameBossHealthManager {
    
    private Game game;
    private int bossHealth;
    private int maxHealth;
    private Manager manager;

    public GameBossHealthManager(Manager manager, Game game){
        this.game = game;
        this.manager = manager;
    }

    public void damage(int damage){
        bossHealth -= damage;

        //end of phase
        if(bossHealth <= 0){
            game.nextState();
        }
    }
    public int bossHealth(){
        return bossHealth;
    }
    public int bossMaxHealth(){
        return maxHealth;
    }
    public void calcMaxHealth(int numClients, GameState phase){
        switch(phase){
            case PHASE_1:
                maxHealth = 100*numClients;
                break;
            case PHASE_2:
                maxHealth = 200*numClients;
                break;
            default:
                maxHealth = 1;
                break;
        }
    }

    public void resetHealth(){
        bossHealth = maxHealth;
    }

    public void setInvulnerable(boolean invulnerable){
        manager.broadcast(new NetworkObject<Boolean>(invulnerable, Packet.BOSS_INVULNERABLE));
    }
}
