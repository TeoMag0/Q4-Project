import java.net.*;
import java.io.*;

/*
 * purpose: host server
 * things to update: none
 */

public class Server {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		int portNumber = 1024;
		ServerSocket serverSocket = new ServerSocket(portNumber);

        Manager manager = new Manager();
		Game game = new Game(manager);

		//This loop will run and wait for one connection at a time.
		int i=0;
		while(true){
			System.out.println("Waiting for a connection");

			//Wait for a connection.
			Socket clientSocket = serverSocket.accept();

			//Once a connection is made, run the socket in a ServerThread.
            ServerThread serverThread = new ServerThread(clientSocket, game, i);
			game.addClient(i);
            manager.add(serverThread);
			Thread thread = new Thread(serverThread);
			thread.start();
			i++;
		}
	}
}