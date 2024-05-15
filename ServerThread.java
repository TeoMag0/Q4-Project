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

        try{
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
	}

    @SuppressWarnings("rawtypes")
	public void run(){        
		try{
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while(true){
                NetworkObject received = (NetworkObject)in.readObject();
                game.update(clientID, received);
            }
		} catch(SocketException e){
            game.disconnectClient(clientID);
        }catch (IOException e){
            game.disconnectClient(clientID);
            e.printStackTrace();
		}catch(ClassNotFoundException e){
            e.printStackTrace();
        }
	}

    @SuppressWarnings("rawtypes")
    public synchronized void send(NetworkObject packet){
        try{
            out.writeObject(packet);
        } catch (IOException e){
            System.out.println("Error sending message");
            e.printStackTrace();
        }
    }

    public int clientID(){
        return clientID;
    }
}