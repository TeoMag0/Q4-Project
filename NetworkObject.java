import java.io.Serializable;

/*
 *purpose: act as a container when sending data between client/servers
 things to update:  
 */

public class NetworkObject<E> implements Serializable{
    public final E data;
    public final Packet packet;

    public NetworkObject(E data, Packet packet){
        this.data = data;
        this.packet = packet;
    }
}
