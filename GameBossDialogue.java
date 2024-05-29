public class GameBossDialogue {
    private Manager manager;

    public GameBossDialogue(Manager manager, Game game){
        this.manager = manager;
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
                default:
                    break;
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void phase1Dialogue() throws InterruptedException{
        Thread.sleep(2000);
        sendMessage("Hello my little computer science students. :)");
        sendMessage("Welcome to the quarter 4 project.");
        sendMessage("Use left click to attack, and WASD to move around.");
        sendMessage("In order to pass, you have to defeat...");
        sendMessage("ME.");
    }
    private void phase2Dialogue() throws InterruptedException{
        Thread.sleep(2000);
        sendMessage("Ew this code is so messy.");
    }
    private void gameEndDialogue() throws InterruptedException{
        sendMessage("I'm dying");
    }

    private void sendMessage(String message)throws InterruptedException{
        manager.broadcast(new NetworkObject<String>(message, Packet.BOSS_QUOTE));
        Thread.sleep(3000);
    }
}
