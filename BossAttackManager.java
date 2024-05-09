public class BossAttackManager{
    
    private BossTentacleAttack tentacles;
    private BossDeathLinesAttack deathLines;
    private Boss boss;

    public BossAttackManager(Boss boss){
        this.boss = boss;
        tentacles = new BossTentacleAttack(boss);
        deathLines = new BossDeathLinesAttack(boss);
    }

    public void activateTentacles(){
        tentacles.setActive(true);
    }
    public void activateDeathLines(){
        deathLines.setActive(true);
    }
}
