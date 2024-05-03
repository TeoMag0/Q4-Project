public class MyHashTable<K,V>{
    private DLList<Pair<K,V>>[] array;
    private MyHashSet<K> keySet;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity){
        array = new DLList[capacity];
        keySet = new MyHashSet<K>(capacity);
        size = 0;
    }

    public void put(K key, V val){
        if(keySet.contains(key)){
            remove(key);
        }
        keySet.add(key);
        if(val == null){
            return;
        }
        if(array[key.hashCode()%array.length] == null){
            array[key.hashCode()%array.length] = new DLList<Pair<K,V>>();
        } 
        array[key.hashCode()%array.length].add(new Pair<K, V>(key, val));    
        size++;
    }
    public V get(K key){
        DLList<Pair<K, V>> list = array[key.hashCode()%array.length];
        if(list != null){
            for(Pair<K,V> each : list){
                if(each.key.equals(key)){
                    return each.val;
                }
            }
        }
        return null;
    }

    public V remove(K key){
        V toReturn = get(key);
        array[key.hashCode()%array.length].remove(new Pair<K,V>(key, toReturn));
        if(array[key.hashCode()%array.length].size() == 0){
            keySet.remove(key);
        }
        if(toReturn != null){
            size--;
        }
        return toReturn;
    }

    @SuppressWarnings("unchecked")
    public void clear(){
        keySet.clear();
        array = new DLList[array.length];
        size = 0;
    }

    public MyHashSet<K> keySet(){
        return keySet;
    }
    public int size(){
        return size;
    }

    @Override
    public String toString(){
        String s = "";
        for(K each : keySet.toDLList()){
            s += get(each)+"\n";
        }
        return s;
    }
}
