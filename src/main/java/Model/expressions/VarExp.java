package Model.expressions;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.MyException;
import Model.types.Type;
import Model.values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String _id){
        id = _id;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        return tbl.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
