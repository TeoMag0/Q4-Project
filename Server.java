import java.net.*;
import java.io.*;
import java.util.Scanner;

/*
 * purpose: host server
 * things to update: none
 */

public class Server {
	private static ServerSocket serverSocket;
	private static Game game;
	private static Manager manager;
	public static void main(String[] args) throws IOException {
		int portNumber = 1024;
		serverSocket = new ServerSocket(portNumber);

        manager = new Manager();

		System.out.println("How many players? (1-4)");
		Scanner scan = new Scanner(System.in);
		int maxP = Integer.parseInt(scan.nextLine());
		game = new Game(manager, maxP);
		scan.close();

		System.out.println("Waiting for a connection");
		waitForPlayers();
		
	}
	public static void waitForPlayers() throws IOException{
		int i=0;
		while (game.numClients() < game.MaxPlayers) {
			if(game.clients().get(i) == null){
				// Wait for a connection.
				Socket clientSocket = serverSocket.accept();

				// Once a connection is made, run the socket in a ServerThread.
				ServerThread serverThread = new ServerThread(clientSocket, game, i);
				manager.add(serverThread);
				Thread thread = new Thread(serverThread);
				thread.start();
				game.addClient(i);
				i++;
			}else{
				i++;
			}
			if(i >= game.MaxPlayers){
				i = 0;
			}
		}
	}
}