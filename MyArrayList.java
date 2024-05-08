import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E>{

    private Object[] array;
    private int size = 0;

    public MyArrayList(){
        array = new Object[4];
    }
    public MyArrayList(int s){
        array = new Object[s];
    }
    public MyArrayList(Object[] arrayIn){
        array = arrayIn.clone();
    }

    @SuppressWarnings("unchecked")
    public E get(int index){
        return (E)array[index];
    }
    public boolean add(E obj){
        if(size == array.length)
            doubleArraySize();
        try{
            array[size] = obj;
            size++;
            return true;
        }catch(Exception e){return false;}
    }
    public void add(int index, E obj){
        if(size == array.length)
            doubleArraySize();

        if(index >= size)
            array[size] = obj;
        else{
            for(int i=size;i>index;i--){
            array[i] = array[i-1];
            }
            array[index] = obj;
        }
        size++;
    }
    public void set(int index, E obj){
        array[index] = obj;
    }
    public void remove(int index){
        array[index] = null;
        for(int i=index;i<size-1;i++){
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--;
    }
    public void remove(E element){
        for(int i=0;i<size;i++){
            if(array[i].equals(element)){
                remove(i);
                return;
            }
        }
    }
    public int size(){
        return size;
    }
    public String toString(){
        String s = "";
        for(int i=0;i<size;i++){
            s += array[i].toString()+"\n";
        }
        return s;
    }

    private void doubleArraySize(){
        Object[] temp = array;
        array = new Object[array.length * 2];
        for(int i=0;i<temp.length;i++){
            array[i] = temp[i];
        }
    }

    //iterator to use foreach
    @Override @SuppressWarnings("unchecked")
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator<E>(){
            private int index = -1;

            public boolean hasNext(){
                if(index+1 < size()){
                    return true;
                }
                return false;
            }

            public E next(){
                index++;
                return (E)array[index];
            }
        };
        return it;
    }
}
