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
        sendMessage("je voudrais un croissant");
        Thread.sleep(5000);
    }
    private void phase2Dialogue() throws InterruptedException{
        sendMessage("I hope you aren't claustrophobic, because YOU'RE ABOUT TO GET SERIALIZED");
        Thread.sleep(5000);
    }
    private void gameEndDialogue() throws InterruptedException{
        sendMessage("I'm dying");
        Thread.sleep(5000);
    }

    private void sendMessage(String message){
        manager.broadcast(new NetworkObject<String>(message, Packet.BOSS_QUOTE));
    }
}
