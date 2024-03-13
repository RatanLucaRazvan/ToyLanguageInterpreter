package DataStructures;

import Exceptions.FullCapacityExc;
import Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyFileTable<K, V> implements MyIDictionary<K, V>{
    HashMap<K, V> dictionary;
    int capacity;

    public MyFileTable() {
        dictionary = new HashMap<K, V>();
        capacity = 10;
    }

    @Override
    public Object get(Object key) {
        return dictionary.get(key);
    }

    @Override
    public void update(K key, V value) throws FullCapacityExc {
        if(!isDefined(key) && dictionary.size() + 1 > capacity)
            throw new FullCapacityExc("SymTable is full!");
        dictionary.put(key, value);
    }

    @Override
    public void remove(K key) {
        dictionary.remove(key);
    }

    @Override
    public boolean isDefined(K id) {
        if(dictionary.get(id) == null)
            return false;
        return true;
    }

    @Override
    public V lookup(K id) throws MyException {
        if(dictionary.get(id) == null)
            throw new MyException("ID does not exist");
        else
            return dictionary.get(id);
    }

    @Override
    public String transformString() {
        StringBuilder result = new StringBuilder();
        if(dictionary.isEmpty())
        {
            return "No variables yet";
        }
        int current = 0;
        for(K key : dictionary.keySet())
        {
            current++;
            result.append(key);
            if(current != dictionary.keySet().size())
                result.append("\n");
        }
        return result.toString();
    }

    @Override
    public Map<K, V> getContent() {
        return dictionary;
    }

    @Override
    public MyIDictionary<K, V> getCopy() throws FullCapacityExc {
        MyIDictionary<K, V> newDict = new MyDictionary<>();

        for(K key: this.dictionary.keySet()){
            newDict.update(key, this.dictionary.get(key));
        }

        return newDict;
    }
}
