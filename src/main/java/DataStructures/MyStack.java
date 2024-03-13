package DataStructures;

import Exceptions.EmptyCollectionExc;
import Exceptions.FullCapacityExc;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    Stack<T> stack;
    int capacity;

    public MyStack(){
        stack = new Stack<T>();
        capacity = 20;
    }
    @Override
    public T pop() throws EmptyCollectionExc {
        if(stack.isEmpty())
            throw new EmptyCollectionExc("Execution Stack is empty");
        return stack.pop();
    }

    @Override
    public void push(T v) throws FullCapacityExc {
        if(stack.size() + 1 > capacity)
            throw new FullCapacityExc("Exec stack is full!");
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String transformString() {
//        if(!stack.isEmpty())
//            return stack.peek().toString();
//        return "empty stack";
        StringBuilder result = new StringBuilder();
        for(T elem:  stack){
            result.append(elem.toString() + "\n");
        }
        return result.toString();
    }

    @Override
    public Stack<T> getContent() {
        return this.stack;
    }
}
