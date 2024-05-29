import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class InteractionPrompt implements DrawableObject, Transform {
    
    private Transform parent;
    private Vector2 offset;
    private BufferedImage sprite;
    private Vector2 size;
    private String purpose;

    public InteractionPrompt(Transform parent, Vector2 offset, String purpose){
        this.parent = parent;
        this.offset = offset.clone();
        this.purpose = purpose;

        size = new Vector2(.3f, .3f);

        try{
            sprite = ImageIO.read(new File("Interact.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g){
        int fontSize = 24;
        float outlineSize = 4;
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
        float padding = .05f;

        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(getPos(), new Vector2(-(size.getX()+Screen.toCoords(g.getFontMetrics().stringWidth(purpose))+padding)/2,size.getY()/2)));
        g.drawImage(sprite, drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size.getX()), Screen.toPixels(size.getY()), null);

        Vector2 purposeOutlineDrawPoint = Screen.getScreenCoords(new Vector2(Screen.getWorldCoords(drawPoint).getX()+size.getX()+padding, getPos().getY()-Screen.toCoords(g.getFontMetrics().getAscent())/2));
        g.drawString(purpose, purposeOutlineDrawPoint.intX(), purposeOutlineDrawPoint.intY());

        /*Vector2 purposeTextDrawPoint = Vector2.sum(purposeOutlineDrawPoint, new Vector2(outlineSize/2, -outlineSize/2));
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, (int)(fontSize-outlineSize)));
        g.drawString(purpose, purposeTextDrawPoint.intX(), purposeTextDrawPoint.intY());*/
    }
    public Vector2 getPos(){
        return Vector2.sum(parent.getPos(), offset);
    }
}
