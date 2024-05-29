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

    private int getIndex(E obj){
        return Math.abs(obj.hashCode()%array.length);
    }

    public E add(E obj){
        E toReturn = get(obj);
        if(toReturn != null){
            remove(toReturn);
        }
        if(array[getIndex(obj)] == null){
            array[getIndex(obj)] = new DLList<E>();
        }
        array[getIndex(obj)].add(obj);
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
        array[getIndex(obj)].remove(obj);
        if(array[getIndex(obj)].size() == 0){
            array[getIndex(obj)] = null;
        }
        
        list.remove(obj);
        size--;
        return toReturn;
    }

    public boolean contains(E obj){
        return get(obj) != null ? true : false;
    }

    private E get(E obj){
        DLList<E> bucketList = array[getIndex(obj)];
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
