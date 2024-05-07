import java.net.*;
import java.io.*;

/*
 * purpose: host server
 * things to update: none
 */

public class Server {
	private static int i = 0;
	public static void main(String[] args) throws IOException {
		int portNumber = 1024;
		ServerSocket serverSocket = new ServerSocket(portNumber);

        Manager manager = new Manager();
		Game game = new Game(manager);

		System.out.println("Waiting for a connection");
		waitForPlayers(game, manager, serverSocket);
		
	}
	private static void waitForPlayers(Game game, Manager manager, ServerSocket serverSocket) throws IOException{
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