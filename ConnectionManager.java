import java.io.*;
import java.net.*;

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

		try {

			while (true) {
				Object receivedObject = in.readObject();
				NetworkObject received = (NetworkObject)receivedObject;

				switch(received.objectType()){
					case "":
                        break;
				}
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
