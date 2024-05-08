public class BossAttackManager{
    
    private BossTentacleAttack tentacles;
    private Boss boss;

    public BossAttackManager(Boss boss){
        this.boss = boss;
        tentacles = new BossTentacleAttack(boss);
    }

    public void activateTentacles(){
        tentacles.setActive(true);
    }
}
