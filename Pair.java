import java.io.Serializable;

public class Pair<K,V> implements Serializable{
    public final K key;
    public final V val;

    public Pair(K k, V v){
        key = k;
        val = v;
    }

    @Override @SuppressWarnings("unchecked")
    public boolean equals(Object o){
        Pair<K,V> e = (Pair<K,V>)o;
        return key.equals(e.key) && val.equals(e.val);
    }

    public String toString(){
        return "Pair("+key+", "+val+")";
    }
}
