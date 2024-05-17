import java.awt.*;

public class BossSpeechBubble implements DrawableObject, Runnable{
    private Vector2 offset;
    private String quoteSection;
    private Boss boss;
    private float padding;//padding of text box in pixels

    private float quoteDuration;
    private DLList<String> queuedQuotes;
    private Thread activeThread;

    public BossSpeechBubble(Boss boss, Vector2 offset){
        this.offset = offset.clone();
        this.boss = boss;
        padding = 10;
        queuedQuotes = new DLList<>();
        quoteDuration = 3;
        activeThread = null;
    }

    public void drawMe(Graphics g){
        if(queuedQuotes.size() != 0){
            float textWidth = g.getFontMetrics().stringWidth(quoteSection);
            float textHeight = g.getFontMetrics().getHeight();
            Vector2 screenOffset = Screen.getScreenCoords(Vector2.sum(boss.getPos(), offset));
            Vector2 textDrawPoint = new Vector2(screenOffset.getX()-textWidth/2, screenOffset.getY()-padding);

            Vector2 bubbleDrawPoint = new Vector2(screenOffset.getX()-textWidth/2-padding, screenOffset.getY()-2*padding-textHeight);
            Vector2 bubbleSize = new Vector2(2*padding + textWidth, 2*padding + textHeight);

            g.setColor(Color.WHITE);
            g.fillRoundRect(bubbleDrawPoint.intX(), bubbleDrawPoint.intY(), bubbleSize.intX(), bubbleSize.intY(), (int)padding, (int)padding);
            g.setColor(Color.BLACK);
            g.drawString(quoteSection, textDrawPoint.intX(), textDrawPoint.intY());
        }
    }

    public void setQuote(String quote){
        queuedQuotes.add(quote);

        if(activeThread == null){
            activeThread = new Thread(this);
            activeThread.start();
        }
    }

    public void run(){
        try{
            while(queuedQuotes.size() != 0){
                String quote = queuedQuotes.get(0);
                quoteSection = "";
                for(int i=0;i<quote.length();i++){
                    quoteSection += quote.charAt(i);
                    Thread.sleep(10);
                }
                Thread.sleep((int)(quoteDuration*1000));
                queuedQuotes.remove(0);
            }
            activeThread = null;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
