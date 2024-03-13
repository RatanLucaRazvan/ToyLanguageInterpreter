package DataStructures;

import Exceptions.EmptyCollectionExc;
import Exceptions.FullCapacityExc;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> list;
    int capacity;


    public MyList(){
        list = new ArrayList<T>();
        capacity = 10;
    }
    @Override
    public boolean remove(T value) throws EmptyCollectionExc {
        if(list.isEmpty())
            throw new EmptyCollectionExc("The output list is empty");
        return list.remove(value);
    }

    @Override
    public void add(T value) throws FullCapacityExc {
        if(list.size() + 1 > capacity)
            throw new FullCapacityExc("Output list is full!");
        list.add(value);
    }

    @Override
    public List<T> getContent() {
        return this.list;
    }

    @Override
    public String transformString() {
        StringBuilder result = new StringBuilder();
        if(list.isEmpty())
        {
            return "No outputs yet\n";
        }
        for(T elem : list)
        {
            result.append(elem + "\n");
        }

        return result.toString();
    }
}
