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
                default:
                    break;
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void phase1Dialogue() throws InterruptedException{
        game.sendBossMaxHealth(GameState.PHASE_1);
        Thread.sleep(2000);
        sendMessage("Hello my little computer science students. :)");
        sendMessage("Welcome to the quarter 4 project.");
        sendMessage("Use left click to attack, and WASD to move around.");
        sendMessage("In order to pass, you have to defeat...");
        sendMessage("me.");
    }
    private void phase2Dialogue() throws InterruptedException{
        Thread.sleep(2000);
        sendMessage("You did pretty good.");
        sendMessage("EXCEPT FOR THE FACT THAT YOUR CODE IS A MESS!!!");
        sendMessage("I'VE NEVER SEEN THIS MANY ERRORS IN MY ENTIRE LIFE!");
        sendMessage("I WON'T GO THAT EASY ON YOU!");
        sendMessage("AND I'D BETTER NOT CATCH YOU COPYING CODE!");
        game.sendBossMaxHealth(GameState.PHASE_2);
        game.transformBoss();
    }
    private void gameEndDialogue() throws InterruptedException{
        sendMessage("You did good.");
        sendMessage("Just go ahead and take your A+.");
    }

    private void sendMessage(String message)throws InterruptedException{
        manager.broadcast(new NetworkObject<String>(message, Packet.BOSS_QUOTE));
        Thread.sleep(3000);
    }
}
