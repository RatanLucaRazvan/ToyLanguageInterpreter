package DataStructures;

import Exceptions.MyException;

import java.util.HashMap;

public class MyBarrierTable<K, V> implements MyIBarrierTable<K, V>{
    private HashMap<K, V> barrierTable;
    private int freeLocation = 0;

    public MyBarrierTable(){
        this.barrierTable = new HashMap<>();
    }
    @Override
    public void put(K key, V value) throws MyException {
        synchronized (this){
            if(!barrierTable.containsKey(key)){
                barrierTable.put(key, value);
            } else{
                throw new MyException("Barrier table already contains the key " + key);
            }
        }
    }

    @Override
    public V get(K key) throws MyException {
        synchronized (this){
            if(barrierTable.containsKey(key)){
                return barrierTable.get(key);
            } else{
                throw new MyException("Barrier table does not contain the key " + key);
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        synchronized (this){
            return barrierTable.containsKey(key);
        }
    }

    @Override
    public int getFreeAddress() {
        synchronized (this){
            freeLocation++;
            return freeLocation;
        }
    }

    @Override
    public void setFreeAddress(int freeAddress) {
        synchronized (this){
            this.freeLocation = freeAddress;
        }
    }

    @Override
    public void update(K key, V value) throws MyException {
        synchronized (this){
            if(barrierTable.containsKey(key)){
                barrierTable.replace(key, value);
            } else {
                throw new MyException("Barrier table does not contain the key " + key);
            }
        }
    }

    @Override
    public HashMap<K, V> getBarrierTable() {
        synchronized (this){
            return barrierTable;
        }
    }

    @Override
    public void setBarrierTable(HashMap<K, V> newBarrierTable) {
        synchronized (this){
            this.barrierTable = newBarrierTable;
        }
    }

    @Override
    public String transformString() {
        StringBuilder result = new StringBuilder();
        if(barrierTable.isEmpty())
        {
            return "No variables yet";
        }
        int current = 0;
        for(K key : barrierTable.keySet())
        {
            current++;
            result.append(key + " --> " + barrierTable.get(key));
            if(current != barrierTable.keySet().size())
                result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return this.barrierTable.toString();
    }
}