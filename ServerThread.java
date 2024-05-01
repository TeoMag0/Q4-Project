import java.net.*;
import java.io.*;

public class ServerThread implements Runnable{	
	private Socket clientSocket;
    private Manager manager;

	public ServerThread(Socket clientSocket, Manager manager){
		this.clientSocket = clientSocket;
        this.manager = manager;
	}

    @SuppressWarnings("rawtypes")
	public void run(){
		System.out.println(Thread.currentThread().getName() + ": connection opened.");
		try{
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while(true){
                Object receivedObject = in.readObject();
                NetworkObject received = (NetworkObject)receivedObject;

            }
            //out.flush();
            //out.close();
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
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(packet);
        } catch (IOException ex){
            System.out.println("Error sending message");
            System.out.println(ex.getMessage());
        }
    }
}