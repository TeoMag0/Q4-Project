public class MyHashSet<E>{
    private DLList<E>[] array;
    private DLList<E> list;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashSet(int capacity){
        array = new DLList[capacity];
        list = new DLList<E>();
        size = 0;
    }

    public E add(E obj){
        E toReturn = get(obj);
        if(toReturn != null){
            remove(toReturn);
        }
        if(array[obj.hashCode()%array.length] == null){
            array[obj.hashCode()%array.length] = new DLList<E>();
        }
        array[obj.hashCode()%array.length].add(obj);
        size++;
        list.add(obj);
        return toReturn;
    }

    public E remove(E obj){
        E toReturn = (E)get(obj);
        if(toReturn == null){
            System.out.println("tried to remove a key from hashtable that doesn't exist");
            return null;
        }
        array[obj.hashCode()%array.length].remove(obj);
        if(array[obj.hashCode()%array.length].size() == 0){
            array[obj.hashCode()%array.length] = null;
        }
        
        list.remove(obj);
        size--;
        return toReturn;
    }

    public boolean contains(E obj){
        return get(obj) != null ? true : false;
    }

    private E get(E obj){
        DLList<E> bucketList = array[obj.hashCode()%array.length];
        if(bucketList != null){
            for(E each : bucketList){
                if(obj.equals(each)){
                    return each;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void clear(){
        array = new DLList[array.length];
        list.clear();
        size = 0;
    }

    public DLList<E> toDLList(){
        return list;
    }

    public int size(){
        return size;
    }
}
