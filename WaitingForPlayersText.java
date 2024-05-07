import java.awt.*;

/*
 * the text that says "waiting for players: x/4"
 */

public class WaitingForPlayersText implements DrawableObject, Runnable{
    private boolean enabled;
    private int playerCount;
    private int maxPlayers;
    private String string;
    private Color textColor;
    private float textFadeTime;

    public WaitingForPlayersText(boolean enabled){
        this.enabled = enabled;
        playerCount = 0;
        maxPlayers = 0;
        textColor = Color.WHITE;
        textFadeTime = 2;
        updateString();
    }

    public void drawMe(Graphics g){
        if (enabled) {
            g.setFont(new Font(Font.DIALOG, Font.PLAIN, 36));
            g.setColor(textColor);
            g.drawString(string, (int)((Screen.screenPixelDimensions.getX()-g.getFontMetrics().stringWidth(string))/2), (int)(Screen.screenPixelDimensions.getY()/3));
        }
    }

    public void setCurrentPlayerCount(int count){
        playerCount = count;
        updateString();
    }
    public void setMaxPlayers(int max){
        maxPlayers = max;
        updateString();
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    public void updateString(){
        string = String.format("Waiting for Players: %s/%s", playerCount, maxPlayers);
    }

    public void fadeOut(){
        new Thread(this).start();
    }

    public void run(){
        try{
            int deltaTime = (int)(textFadeTime/255*1000);
            Thread.sleep(1000);//start delay
            while(textColor.getAlpha() > 0){
                textColor = new Color(textColor.getRed(), textColor.getGreen(), textColor.getBlue(), textColor.getAlpha()-1);
                Thread.sleep(deltaTime);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}