public class BossAttackManager{
    
    private BossTentacleAttack tentacles;
    private BossDeathLinesAttack deathLines;
    private BossHashMapAttack hashmapAttack;
    private BossRecursiveAttack recursiveAttack;
    private BossStackOverflowAttack stackOverflow;
    private Boss boss;

    public BossAttackManager(Boss boss){
        this.boss = boss;
        tentacles = new BossTentacleAttack(boss);
        deathLines = new BossDeathLinesAttack(boss);
        hashmapAttack = new BossHashMapAttack(boss, 2);
        recursiveAttack = new BossRecursiveAttack(boss);
        stackOverflow = new BossStackOverflowAttack(boss);
    }

    public void startAttack(BossAttacks attack){
        switch(attack){
            case TENTACLE:
                tentacles.setActive(true);
                break;
            case DEATH_LINE_TOP:
                deathLines.setDirection(BossDeathLinesAttack.Direction.TOP);
                deathLines.setActive(true);
                break;
            case DEATH_LINE_LEFT:
                deathLines.setDirection(BossDeathLinesAttack.Direction.LEFT);
                deathLines.setActive(true);
                break;
            case DEATH_LINE_RIGHT:
                deathLines.setDirection(BossDeathLinesAttack.Direction.RIGHT);
                deathLines.setActive(true);
                break;
            case HASHMAP:
                hashmapAttack.setActive(true);
                break;
            case RECURSIVE:
                recursiveAttack.setActive(true);
                break;
            case STACK_OVERFLOW:
                stackOverflow.setActive(true);
                break;
        }
    }
    public void stopAttack(BossAttacks attack){
        switch (attack) {
            case TENTACLE:
                tentacles.setActive(false);
                break;
            case DEATH_LINE_TOP, DEATH_LINE_LEFT, DEATH_LINE_RIGHT:
                deathLines.setActive(false);
                break;
            case HASHMAP:
                hashmapAttack.setActive(false);
                break;
            case RECURSIVE:
                recursiveAttack.setActive(false);
                break;
            case STACK_OVERFLOW:
                stackOverflow.setActive(false);
                break;
        }
    }

    public void setHashmapPositions(Vector2[] array){
        hashmapAttack.setPlayerPositions(array);
    }
}
