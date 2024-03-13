package DataStructures;

import Exceptions.FullCapacityExc;
import Exceptions.MyException;

import java.util.Map;

public interface MyIDictionary<K, V> {
    V get(K key);
    void update(K key, V value) throws FullCapacityExc;

    void remove(K key);

    boolean isDefined(K id);

    V lookup(K id) throws MyException;

    String transformString();

    Map<K, V> getContent();

    public MyIDictionary<K, V> getCopy() throws FullCapacityExc;
}
