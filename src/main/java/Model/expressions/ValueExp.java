package Model.expressions;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.MyException;
import Model.types.Type;
import Model.values.Value;

public class ValueExp implements Exp{
    Value e;

    public ValueExp(Value _e){
        e = _e;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        return e;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
