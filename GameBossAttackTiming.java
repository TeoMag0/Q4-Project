public class GameBossAttackTiming implements Runnable{
    
    private Thread activeThread = null;
    private float attackDuration;
    private Manager manager;
    private BossAttacks lastAttack;
    private Game game;

    public GameBossAttackTiming(Manager manager, Game game){
        attackDuration = 10;
        this.manager = manager;
        this.game = game;
    }

    public void run(){
        try{
            while(activeThread != null){
                BossAttacks attack = pickRandomAttack();
                lastAttack = attack;
                startAttack(attack);
                Thread.sleep((int)(1000*attackDuration));
                stopAttack(attack);
                Thread.sleep(3000);
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

    public void setActive(boolean active){
        if(active && activeThread == null){
            activeThread = new Thread(this);
            activeThread.start();
        }else if(!active && activeThread != null){
            activeThread = null;
        }
    }
}
