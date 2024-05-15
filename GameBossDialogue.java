public class GameBossDialogue {
    private Manager manager;
    private Game game;

    public GameBossDialogue(Manager manager, Game game){
        this.manager = manager;
        this.game = game;
    }

    public void startOfStateDialogue(GameState state){
        switch(state){
            case PHASE_1:
            //send message
                break;
            case PHASE_2:
                break;
            case GAME_END:
                break;
        }
    }
}
