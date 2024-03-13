package Model.expressions;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.DivisionByZeroExc;
import Exceptions.MyException;
import Model.types.IntType;
import Model.types.Type;
import Model.values.IntValue;
import Model.values.Value;

import java.util.Objects;

public class ArithExp implements Exp{
    Exp e1;
    Exp e2;

    String op;

    public ArithExp(String _op, Exp _e1, Exp _e2) {
        e1 = _e1;
        e2 = _e2;
        op = _op;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception{
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(Objects.equals(op, "+")) return new IntValue(n1+n2);
                if(Objects.equals(op, "-")) return new IntValue(n1-n2);
                if(Objects.equals(op, "*")) return new IntValue(n1*n2);
                if(Objects.equals(op, "/")){
                    if(n2 == 0) throw new DivisionByZeroExc("Cannot divide by zero");
                    else
                        return new IntValue(n1/n2);}
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        return v1;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())) {
                return new IntType();
            }else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString() {
        if(Objects.equals(op, "+"))
        {
            return e1 + " + " + e2;
        }
        if(Objects.equals(op, "-"))
        {
            return e1 + " - " + e2;
        }
        if(Objects.equals(op, "*"))
        {
            return e1 + " * " + e2;
        }
        if(Objects.equals(op, "/"))
        {
            return e1 + " / " + e2;
        }
        return null;
    }
}
