public class GameBossDialogue {
    private Manager manager;
    private Game game;

    public GameBossDialogue(Manager manager, Game game){
        this.manager = manager;
        this.game = game;
    }

    public void startOfStateDialogue(GameState state){
        try{
            switch(state){
                case PHASE_1:
                    phase1Dialogue();
                    break;
                case PHASE_2:
                    phase2Dialogue();
                    break;
                case GAME_END:
                    gameEndDialogue();
                    break;
            }
        }catch(InterruptedException e){

        }
    }

    private void phase1Dialogue() throws InterruptedException{
        manager.broadcast(new NetworkObject<String>("Je voudrais un croissant.", Packet.BOSS_QUOTE));
        manager.broadcast(new NetworkObject<String>("N'IMPORTE QUOI", Packet.BOSS_QUOTE));
        Thread.sleep(5000);
    }
    private void phase2Dialogue() throws InterruptedException{
        manager.broadcast(new NetworkObject<String>("I HOPE YOU'RE READY TO GET SERIALIZED", Packet.BOSS_QUOTE));
        Thread.sleep(5000);
    }
    private void gameEndDialogue() throws InterruptedException{
        Thread.sleep(5000);
    }
}
