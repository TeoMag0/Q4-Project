import java.io.Serializable;

public abstract class NetworkObject<E> implements Serializable{
    public abstract String objectType();
    public final E data;
    public final Packet packet;

    public NetworkObject(E data, Packet packet){
        this.data = data;
        this.packet = packet;
    }
}
