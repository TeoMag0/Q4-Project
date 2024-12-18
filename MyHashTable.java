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

    private int keyIndex(K key){
        return Math.abs(key.hashCode()%array.length);
    }

    public void put(K key, V val){
        if(keySet.contains(key)){
            remove(key);
        }
        if(val == null){
            return;
        }
        if(array[keyIndex(key)] == null){
            array[keyIndex(key)] = new DLList<Pair<K,V>>();
        } 
        array[keyIndex(key)].add(new Pair<K, V>(key, val));    
        keySet.add(key);
        size++;
    }
    public V get(K key){
        DLList<Pair<K, V>> list = array[keyIndex(key)];
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
        array[keyIndex(key)].remove(new Pair<K,V>(key, toReturn));
        keySet.remove(key);
        if(array[keyIndex(key)].size() == 0){
            array[keyIndex(key)] = null;
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
