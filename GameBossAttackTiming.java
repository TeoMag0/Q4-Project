public class GameBossAttackTiming implements Runnable{
    
    private Thread activeThread = null;
    private float attackDuration;
    private Manager manager;

    public GameBossAttackTiming(Manager manager){
        attackDuration = 10;
        this.manager = manager;
    }

    public void run(){
        try{
            while(activeThread != null){
                BossAttacks attack = pickRandomAttack();
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
        return attacks[rand];
    }

    private void startAttack(BossAttacks attack){
        manager.broadcast(new NetworkObject<BossAttacks>(attack, Packet.BOSS_ATTACK_START));
        System.out.println("Started Attack: "+attack);
    }
    private void stopAttack(BossAttacks attack){
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