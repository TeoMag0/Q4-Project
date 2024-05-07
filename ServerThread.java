import java.net.*;
import java.io.*;

/*
 * purpose: manage the connection to its client
 * things to update:
 */

public class ServerThread implements Runnable{	
	private Socket clientSocket;
    private ObjectOutputStream out;
    private int clientID;
    private Game game;

	public ServerThread(Socket clientSocket, Game game, int clientID){
		this.clientSocket = clientSocket;
        this.clientID = clientID;
        this.game = game;
	}

    @SuppressWarnings("rawtypes")
	public void run(){
		System.out.println(Thread.currentThread().getName() + ": connection opened.");
		try{
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while(true){
                Object receivedObject = in.readObject();
                NetworkObject received = (NetworkObject)receivedObject;
                game.update(clientID, received);
            }
		} catch (IOException ex){
			System.out.println("Error listening for a connection");
			System.out.println(ex.getMessage());
		}catch(ClassNotFoundException e){
            System.out.println(e);
        }
	}

    @SuppressWarnings("rawtypes")
    public void send(NetworkObject packet){
        try{
            out.writeObject(packet);
        } catch (IOException ex){
            System.out.println("Error sending message");
            System.out.println(ex);
            game.disconnectClient(clientID);
        }
    }

    public int clientID(){
        return clientID;
    }
}