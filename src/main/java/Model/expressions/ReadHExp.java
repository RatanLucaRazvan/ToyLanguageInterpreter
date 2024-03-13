package Model.expressions;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.MyException;
import Model.types.RefType;
import Model.types.Type;
import Model.values.RefValue;
import Model.values.Value;

public class ReadHExp implements Exp{
    Exp exp;

    public ReadHExp(Exp _exp){
        exp = _exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        Value v = exp.eval(tbl, heap);
        if(v.getType().equals(new RefType(((RefValue)v).getLocationType()))){
            int address = ((RefValue) v).getAddress();
            if(heap.isDefined(address)){
                return heap.get(address);
            }
            else
                throw new MyException("Not defined in heap!");
        }
        else
            throw new MyException("Incorrect Value!");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = exp.typecheck(typeEnv);
        if(typ instanceof RefType){
            RefType reft = (RefType) typ;
            return reft.getInner();
        }else
            throw new MyException("The read argument is not a RefType");
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }
}
