package DataStructures;

import Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K, V> implements MyIHeap<K, V>{
    Map<K, V> dict;
    int firstFreePos = 1;

    public MyHeap(){
        dict = new HashMap<>();
    }
    @Override
    public V get(K key) {
        return dict.get(key);
    }

    @Override
    public void update(K key, V value) throws MyException {
//        if(!isDefined(key))
//            throw new MyException("Key is not defined");
        dict.put(key, value);
    }

    @Override
    public void remove(K key) {
        dict.remove(key);
    }

    @Override
    public boolean isDefined(K id) {
        if(dict.get(id) == null)
            return false;
        return true;
    }

    @Override
    public int firstFree() {
        int positionCopy = firstFreePos;
        this.setFirstFree();
        return positionCopy;
    }

    @Override
    public void setFirstFree() {
        firstFreePos = firstFreePos + 1;
    }

    @Override
    public V lookup(K id) throws MyException {
        if(dict.get(id) == null)
            throw new MyException("ID does not exist");
        else
            return dict.get(id);
    }

    @Override
    public Map<K, V> getContent() {
        return dict;
    }

    @Override
    public void setContent(Map<K, V> newHeap) {
        dict.clear();
        dict.putAll(newHeap);
    }

    @Override
    public String transformString() {
        StringBuilder result = new StringBuilder();
        if(dict.isEmpty())
        {
            return "No variables yet";
        }
        int current = 0;
        for(K key : dict.keySet())
        {
            current++;
            result.append(key + " --> " + dict.get(key));
            if(current != dict.keySet().size())
                result.append("\n");
        }
        return result.toString();
    }
}
