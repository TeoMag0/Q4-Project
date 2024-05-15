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
                    break;
                case GAME_END:
                    break;
            }
        }catch(InterruptedException e){

        }
    }

    private void phase1Dialogue() throws InterruptedException{
        Thread.sleep(10000);
    }
}
