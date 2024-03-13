package DataStructures;

import Exceptions.EmptyCollectionExc;
import Exceptions.FullCapacityExc;

import java.util.List;

public interface MyIList<T> {
    boolean remove(T value) throws EmptyCollectionExc;
    void add(T value) throws FullCapacityExc;

    List<T> getContent();

    String transformString();
}
