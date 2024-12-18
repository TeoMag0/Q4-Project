import javax.swing.*;
import java.awt.*;

/*
 * purpose: draw all things that need to be drawn, also wakes up everything and is a hub
 * things to update:
 * notes: 
 * 	UI should directly use pixels to draw, while objects in the world should use getScreenCoords()
 */

public class Screen extends JPanel {

	private static int pixelsPerUnit = 100;
	public static boolean drawColliders;
	public static final Vector2 screenPixelDimensions = new Vector2(1280, 720);
	public static final Screen Singleton = new Screen();
	public static final Player player = new Player(Vector2.zero(), 5f);
	public static final Boss boss = new Boss(new Vector2(0, 2.5f));

	public Screen() {
		this.setLayout(null);
		this.setFocusable(true);		

		drawColliders = false;

		//instantiate things that need awakening
		PhysicsManager.Singleton.wakeUp();
		PlayerProjectile.setUpSprites();
		DummyProjectile.setUpSprites();
		HeartPickup.setUpSprites();
		new RestartButton();
		BalloonManager.setUpBalloons();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenPixelDimensions.intX(), screenPixelDimensions.intY());

		TileMap.Singleton.drawMe(g);
		MoveInstructions.drawAll(g);
		Animation.drawAll(g);
		boss.drawMe(g);
		InteractableObject.drawAll(g);
		DummyPlayerManager.Singleton.drawMe(g);
		player.drawMe(g);
		Projectile.drawAll(g);
		BalloonManager.drawAll(g);

		if(drawColliders){
			Collider.drawAll(g);
		}

		player.uiManager.drawMe(g);
	}
    
	public Dimension getPreferredSize() {
        return new Dimension(screenPixelDimensions.intX(),screenPixelDimensions.intY());
	}

	public static Vector2 getWorldCoords(Vector2 coords){
		float newX = (coords.getX()-screenPixelDimensions.getX()/2)/pixelsPerUnit+player.getPos().getX();
		float newY = (screenPixelDimensions.getY()/2-coords.getY())/pixelsPerUnit+player.getPos().getY();
		return new Vector2(newX, newY);
	}
	public static Vector2 getScreenCoords(Vector2 coords){
		int newX = (int)((coords.getX()-player.getPos().getX())*pixelsPerUnit + screenPixelDimensions.getX()/2);
		int newY = (int)(screenPixelDimensions.getY()/2 - (coords.getY()-player.getPos().getY())*pixelsPerUnit);
		return new Vector2(newX, newY);
	}
	public static int toPixels(float num){
		return (int)(num*pixelsPerUnit);
	}
	public static float toCoords(int pixels){
		return (float)pixels/pixelsPerUnit;
	}
	public static void setPixelsPerUnit(int ppu){
		pixelsPerUnit = ppu;
	}

	@Override
	public synchronized void repaint(){
		super.repaint();
	}
}