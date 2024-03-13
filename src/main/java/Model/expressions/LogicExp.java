package Model.expressions;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.MyException;
import Model.types.BoolType;
import Model.types.Type;
import Model.values.BoolValue;
import Model.values.Value;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op; // 1-and, 2-or

    public LogicExp(Exp _e1, Exp _e2, int _op){
        e1 = _e1;
        e2 = _e2;
        op = _op;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        Value v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new BoolType())){
            Value v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1) return new BoolValue(n1 && n2);
                if(op == 2) return new BoolValue(n1 || n2);
            }
            else
                throw new MyException("Operand 2 is not a boolean");

        }
        else
            throw new MyException("Operand 1 is not a boolean");
        return v1;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType())){
            if(typ2.equals(new BoolType())) {
                return new BoolType();
            }else
                throw new MyException("second operand is not a boolean!!!");
        } else
            throw new MyException("first operand is not a boolean!!!!!");
    }

    @Override
    public String toString() {
        if(op == 1)
        {
            return e1 + " and " + e2;
        }
        if(op == 2)
        {
            return e1 + " or " + e2;
        }
        return null;
    }
}
