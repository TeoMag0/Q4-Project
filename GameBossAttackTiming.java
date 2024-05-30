public class GameBossAttackTiming implements Runnable{
    
    private Thread activeThread = null;
    private float attackDuration;
    private Manager manager;
    private BossAttacks lastAttack;
    private Game game;
    private GameState curState;

    public GameBossAttackTiming(Manager manager, Game game){
        attackDuration = 10;
        this.manager = manager;
        this.game = game;
    }

    public void run(){
        try{
            if(curState == GameState.GAME_END){
                game.dialogueManager.startOfStateDialogue(curState);
                game.endGame();
            }else{
                game.dialogueManager.startOfStateDialogue(curState);
                game.bossHealthManager.setInvulnerable(false);
                while(activeThread == Thread.currentThread()){
                    BossAttacks attack = pickRandomAttack();
                    lastAttack = attack;
                    startAttack(attack);

                    game.healManager.spawnHearts();
                    
                    if(curState == GameState.PHASE_2){
                        BossAttacks attack2 = pickRandomAttack();
                        lastAttack = attack2;
                        startAttack(attack2);
                        Thread.sleep((int) (1000 * attackDuration));
                        stopAttack(attack2);
                    }else{
                        Thread.sleep((int) (1000 * attackDuration));
                    }

                    stopAttack(attack);
                    Thread.sleep(2000);
                }
            }

            //start queued thread
            if(activeThread != null){
                activeThread.start();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private BossAttacks pickRandomAttack(){
        BossAttacks[] attacks = BossAttacks.values();
        int rand = (int)(Math.random()*attacks.length);
        if(attacks[rand] != lastAttack){
            return attacks[rand];
        }else{
            return pickRandomAttack();
        }
    }

    private synchronized void startAttack(BossAttacks attack){
        if (attack == BossAttacks.HASHMAP) {
            // send player positions
            manager.broadcast(new NetworkObject<Vector2[]>(game.getPlayerPositions(), Packet.HASHMAP_POSITIONS));
        }
        manager.broadcast(new NetworkObject<BossAttacks>(attack, Packet.BOSS_ATTACK_START));
        System.out.println("Started Attack: "+attack);
    }
    private synchronized void stopAttack(BossAttacks attack){
        manager.broadcast(new NetworkObject<BossAttacks>(attack, Packet.BOSS_ATTACK_END));
        System.out.println("Ended Attack: " + attack);
    }

    public void startPhase(GameState phase){
        //lkets attack finish before starting new phase
        game.bossHealthManager.setInvulnerable(true);
        curState = phase;
        Thread t = activeThread;
        activeThread = new Thread(this);
        if(t == null){
            activeThread.start();
        }
    }
    public void stopPhase(){
        activeThread = null;
    }
}
