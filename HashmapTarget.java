import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: everything to do with the target created by the hashmap attack
 */

public class HashmapTarget extends Animation{

    private Vector2 position;
    private float size;
    private float frameTime;
    private MyArrayList<BufferedImage> spriteList;
    private int currentImage;

    public HashmapTarget(Vector2 position, float size, float animTime){
        this.position = position;
        this.size = size;
        frameTime = animTime/11;
        spriteList = new MyArrayList<>();
        try{
            for(int i=0;i<11;i++){
                spriteList.add(ImageIO.read(new File(String.format("HMAnim%s.png", i+1))));
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        currentImage = 0;
        new Thread(this).start();
    }

    public void drawMe(Graphics g){
        Vector2 drawPoint = Screen.getScreenCoords(Vector2.sum(position, new Vector2(-size / 2, size / 2)));
        g.drawImage(spriteList.get(currentImage), drawPoint.intX(), drawPoint.intY(), Screen.toPixels(size), Screen.toPixels(size), null);
    }

    public void run(){
        try{
            for(int i=0;i<11;i++){
                currentImage = i;
                Thread.sleep((int)(1000*frameTime));
                Screen.Singleton.repaint();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        removeAnimation(this);
    }
}
