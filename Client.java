import java.io.*;

import javax.swing.JFrame;

public class Client {
    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Client");

		frame.add(Screen.Singleton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		ConnectionManager.Singleton.connect();
    }
}
