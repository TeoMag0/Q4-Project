public class BossAttackManager{
    
    private BossTentacleAttack tentacles;
    private BossDeathLinesTop deathLinesTop;
    private BossDeathLinesLeft deathLinesLeft;
    private BossDeathLinesRight deathLinesRight;
    private BossHashMapAttack hashmapAttack;
    private BossRecursiveAttack recursiveAttack;
    private BossStackOverflowAttack stackOverflow;
    private Boss boss;

    public BossAttackManager(Boss boss){
        this.boss = boss;
        tentacles = new BossTentacleAttack(boss);
        deathLinesTop = new BossDeathLinesTop(boss);
        deathLinesLeft = new BossDeathLinesLeft(boss);
        deathLinesRight = new BossDeathLinesRight(boss);
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
                deathLinesTop.setActive(true);
                break;
            case DEATH_LINE_LEFT:
                deathLinesLeft.setActive(true);
                break;
            case DEATH_LINE_RIGHT:
                deathLinesRight.setActive(true);
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
            case DEATH_LINE_TOP:
                deathLinesTop.setActive(false);
                break;
            case DEATH_LINE_LEFT:
                deathLinesLeft.setActive(false);
                break;
            case DEATH_LINE_RIGHT:
                deathLinesRight.setActive(false);
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
