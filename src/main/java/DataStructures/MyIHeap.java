package DataStructures;

import Exceptions.FullCapacityExc;
import Exceptions.MyException;

import java.util.Map;

public interface MyIHeap<K, V> {
    V get(K key);
    void update(K key, V value) throws FullCapacityExc, MyException;

    void remove(K key);

    boolean isDefined(K id);

    public int firstFree();

    public void setFirstFree();

    V lookup(K id) throws MyException;

    Map<K, V> getContent();

    void setContent(Map<K, V> newHeap);

    String transformString();
}
