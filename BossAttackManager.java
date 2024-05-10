public class BossAttackManager{
    
    private BossTentacleAttack tentacles;
    private BossDeathLinesAttack deathLines;
    private Boss boss;

    public BossAttackManager(Boss boss){
        this.boss = boss;
        tentacles = new BossTentacleAttack(boss);
        deathLines = new BossDeathLinesAttack(boss);
    }

    public void startAttack(BossAttacks attack){
        switch(attack){
            case TENTACLE:
                tentacles.setActive(true);
                break;
            case DEATH_LINE:
                deathLines.setActive(true);
                break;
        }
    }
    public void stopAttack(BossAttacks attack){
        switch (attack) {
            case TENTACLE:
                tentacles.setActive(false);
                break;
            case DEATH_LINE:
                deathLines.setActive(false);
                break;
        }
    }
}
