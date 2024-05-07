import java.io.*;
import java.net.*;

/*
 * purpose: manage sending/receiving packets
 * things to update: switch in while loop must be able to receive any type of data
 */

public class ConnectionManager {
    public static final ConnectionManager Singleton = new ConnectionManager();
    private ObjectOutputStream out;

    @SuppressWarnings("rawtypes")
    public void connect() throws IOException{
		String hostName = "localhost"; 
		int portNumber = 1024;
		Socket serverSocket = new Socket(hostName, portNumber);
        out = new ObjectOutputStream(serverSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

		StartManager.Singleton.start();

		try {
			while (true) {
				Object receivedObject = in.readObject();
				NetworkObject received = (NetworkObject)receivedObject;

				int clientID;
				switch(received.packet){
					case PLAYER_POS:
						//{int clientID, Vector2 pos}
						clientID = (int)((Object[])received.data)[0];
						Vector2 pos = (Vector2)((Object[])received.data)[1];
						DummyPlayerManager.Singleton.updatePosition(clientID, pos);
                        break;
					case PLAYER_STATUS:
						//receives {int clientID, boolean alive}
						clientID = (int)((Object[]) received.data)[0];
						boolean alive = (boolean)((Object[]) received.data)[1];
						DummyPlayerManager.Singleton.setAlive(clientID, alive);
				}

				Screen.Singleton.repaint();
			}
		} catch (UnknownHostException e) {
			System.err.println("Host unkown: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}catch (ClassNotFoundException e){
			System.out.println(e);
		}
        serverSocket.close();
	}

    @SuppressWarnings("rawtypes")
    public void sendObject(NetworkObject obj){
        try{
            out.writeObject(obj);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
