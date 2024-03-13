package DataStructures;

import Exceptions.MyException;

import java.util.HashMap;

public interface MyIBarrierTable<K, V> {
    void put(K key, V value) throws MyException;
    V get(K key) throws MyException;
    boolean containsKey(K key);
    int getFreeAddress();
    void setFreeAddress(int freeAddress);
    void update(K key, V value) throws MyException;
    HashMap<K, V> getBarrierTable();
    void setBarrierTable(HashMap<K, V> newBarrierTable);

    String transformString();
}