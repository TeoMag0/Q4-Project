public class GameHealManager {
    
    private Game game;

    public GameHealManager(Game game){
        this.game = game;
    }

    public void spawnHearts(){
        for(int i=0;i<game.numAliveClients();i++){
            float x = (float)Math.random()*10-4;
            float y = (float)Math.random()*10-4;

            spawnHeart(new Vector2(x, y));
        }
    }

    private void spawnHeart(Vector2 position){
        game.sendHeart(position);
    }
}
