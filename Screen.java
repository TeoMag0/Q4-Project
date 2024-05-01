import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

	public Screen() {
		this.setLayout(null);
		this.setFocusable(true);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
    
	public Dimension getPreferredSize() {
        return new Dimension(1280,720);
	}
}