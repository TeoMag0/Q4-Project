import javax.swing.*;
import java.awt.*;

/*
 * purpose: draw all things that need to be drawn
 * things to update:
 * notes: 
 * 	UI should directly use pixel to draw, while objects in the world should use getScreenCoords()
 */

public class Screen extends JPanel {

	private static final int pixelsPerUnit = 100;
	public static final Vector2 screenPixelDimensions = new Vector2(1280, 720);
	public static final Screen Singleton = new Screen();
	private static Player player;

	public Screen() {
		this.setLayout(null);
		this.setFocusable(true);		

		player = new Player(Vector2.zero(), 0.05f);
		addKeyListener(player.movementManager());
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Vector2 ovalPos = getScreenCoords(new Vector2(1,1));
		g.drawOval(ovalPos.intX(), ovalPos.intY(), toPixels(1), toPixels(1));

		player.drawMe(g);
	}
    
	public Dimension getPreferredSize() {
        return new Dimension(screenPixelDimensions.intX(),screenPixelDimensions.intY());
	}

	public static Vector2 getScreenCoords(Vector2 coords){
		int newX = (int)((coords.getX()-player.getPos().getX())*pixelsPerUnit + screenPixelDimensions.getX()/2);
		int newY = (int)(screenPixelDimensions.getY()/2 - (coords.getY()-player.getPos().getY())*pixelsPerUnit);
		return new Vector2(newX, newY);
	}
	public static int toPixels(float num){
		return (int)(num*pixelsPerUnit);
	}
}