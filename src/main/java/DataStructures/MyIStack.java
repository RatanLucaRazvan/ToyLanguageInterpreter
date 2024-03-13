package DataStructures;

import Exceptions.EmptyCollectionExc;
import Exceptions.FullCapacityExc;

import java.util.Stack;

public interface MyIStack<T> {
    T pop() throws EmptyCollectionExc;
    void push(T v) throws FullCapacityExc;

    boolean isEmpty();

    String transformString();

    Stack<T> getContent();
}
