import java.net.*;
import java.io.*;

/*
 * purpose: manage the connection to its client
 * things to update:
 */

public class ServerThread implements Runnable{	
	private Socket clientSocket;
    private Manager manager;
    private ObjectOutputStream out;
    private int clientID;

	public ServerThread(Socket clientSocket, Manager manager, int clientID){
		this.clientSocket = clientSocket;
        this.manager = manager;
        this.clientID = clientID;
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
                switch(received.packet){
                    case PLAYERPOS:
                        //receives Vector2 pos
                        //sends {int clientID, Vector2 pos}
                        manager.broadcastExcept(new NetworkObject<Object[]>(new Object[]{clientID, received.data}, received.packet), clientID);
                        break;
                }
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
            System.out.println(ex.getMessage());
        }
    }

    public int clientID(){
        return clientID;
    }
}