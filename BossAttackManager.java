public class BossAttackManager{
    
    private BossTentacleAttack tentacles;
    private BossDeathLinesAttack deathLines;
    private BossHashMapAttack hashmapAttack;
    private Boss boss;

    public BossAttackManager(Boss boss){
        this.boss = boss;
        tentacles = new BossTentacleAttack(boss);
        deathLines = new BossDeathLinesAttack(boss);
        hashmapAttack = new BossHashMapAttack(boss, 2);
    }

    public void startAttack(BossAttacks attack){
        switch(attack){
            case TENTACLE:
                tentacles.setActive(true);
                break;
            case DEATH_LINE:
                deathLines.setActive(true);
                break;
            case HASHMAP:
                hashmapAttack.setActive(true);
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
            case HASHMAP:
                hashmapAttack.setActive(false);
                break;
        }
    }

    public void setHashmapPositions(Vector2[] array){
        hashmapAttack.setPlayerPositions(array);
    }
}
