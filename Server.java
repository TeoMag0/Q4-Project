import java.net.*;
import java.io.*;

/*
 * purpose: host server
 * things to update: none
 */

public class Server {
	private static int i = 0;
	private static ServerSocket serverSocket;
	private static Game game;
	private static Manager manager;
	public static void main(String[] args) throws IOException {
		int portNumber = 1024;
		serverSocket = new ServerSocket(portNumber);

        manager = new Manager();
		game = new Game(manager);

		System.out.println("Waiting for a connection");
		waitForPlayers();
		
	}
	private static void waitForPlayers() throws IOException{
		while (game.numClients() < game.MaxPlayers) {

			// Wait for a connection.
			Socket clientSocket = serverSocket.accept();

			// Once a connection is made, run the socket in a ServerThread.
			ServerThread serverThread = new ServerThread(clientSocket, game, i);
			manager.add(serverThread);
			Thread thread = new Thread(serverThread);
			thread.start();
			game.addClient(i);
			i++;
		}
	}
}