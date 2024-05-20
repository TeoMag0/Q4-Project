import java.util.Iterator;

public class DLList<E> implements Iterable<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList(){
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        head.setPrev(tail);
        tail.setNext(head);
        tail.setPrev(head);

        size = 0;
    }
    public void add(E data){
        Node<E> newNode = new Node<E>(data);

        newNode.setPrev(tail.prev());
        newNode.prev().setNext(newNode);
        tail.setPrev(newNode);
        newNode.setNext(tail);

        size++;
    }

    public E get(int index){
        Node<E> node = findNodeByIndex(index);
        try{
            return node.get();
        }catch(Exception e){return null;}
    }
    
    public synchronized E remove(int index){
        Node<E> node = findNodeByIndex(index);
        if(node == null) {System.out.println("Object at loc "+index+" in dllist not found, so couldn't be removed");}
        node.prev().setNext(node.next());
        node.next().setPrev(node.prev());
        size--;
        return node.get();
    }

    public synchronized void remove(E el){
        Node<E> node = findNodeWithElement(el);
        if(node == null) {System.out.println("Object "+el.toString()+" in dllist not found, so couldn't be removed");}
        node.prev().setNext(node.next());
        node.next().setPrev(node.prev());
        size--;
    }
    //iterator to use foreach
    @Override
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator<E>(){
            private Node<E> iteratorNode = head;

            public boolean hasNext(){
                if(iteratorNode.next() == tail){
                    return false;
                }
                return true;
            }

            public E next(){
                iteratorNode = iteratorNode.next();
                return iteratorNode.get();
            }
        };
        return it;
    }

    public DLList<E> clone(){
        DLList<E> clone = new DLList<E>();
        for(int i=0;i<size();i++){
            clone.add(get(i));
        }
        return clone;
    }

    public void clear(){
        while(size >0){
            remove(0);
        }
    }

    public void set(int index, E obj){
        findNodeByIndex(index).setData(obj);
    }

    private Node<E> findNodeByIndex(int ind){
        Node<E> curNode;
        if(ind < size/2){
            curNode = head.next();
            for(int i=0;i<size;i++){
                if(i == ind)
                    return curNode;
                curNode = curNode.next();
            }
        }
        else{
            curNode = tail.prev();
            for(int i=size-1;i>=0;i--){
                if(i == ind)
                    return curNode;
                curNode = curNode.prev();
            }
        }
        return null;
    }

    private Node<E> findNodeWithElement(E el){
        Node<E> curNode = head.next();
        for(int i=0;i<size;i++){
            if(curNode.get().equals(el))
                return curNode;
            curNode = curNode.next();
        }
        return null;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        String s = "[ ";
        for(E each : this){
            s += each+", ";
        }
        return s.substring(0, s.length()-2)+" ]";
    }
}
