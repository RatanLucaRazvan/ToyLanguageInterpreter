package Model.expressions;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.MyException;
import Model.types.BoolType;
import Model.types.IntType;
import Model.types.Type;
import Model.values.BoolValue;
import Model.values.IntValue;
import Model.values.Value;

import java.util.Objects;

public class RelationalExp implements Exp{
    Exp e1;
    Exp e2;
    String op;

    public RelationalExp(Exp _e1, Exp _e2, String _op){
        e1 = _e1;
        e2 = _e2;
        op = _op;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new IntType())){
                IntValue int1, int2;
                int1 = (IntValue) v1;
                int2 = (IntValue) v2;
                int number1, number2;
                number1 = int1.getVal();
                number2 = int2.getVal();
                if(Objects.equals(op, "<")) return new BoolValue(number1 < number2);
                if(Objects.equals(op, "<=")) return new BoolValue(number1 <= number2);
                if(Objects.equals(op, "==")) return new BoolValue(number1 == number2);
                if(Objects.equals(op, "!=")) return new BoolValue(number1 != number2);
                if(Objects.equals(op, ">")) return new BoolValue(number1 > number2);
                if(Objects.equals(op, ">=")) return new BoolValue(number1 >= number2);
            }
            else
                throw new MyException("second operand needs to be an Integer");
        }
        else
            throw new MyException("first operand needs to be an Integer");
        return null;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())) {
                return new BoolType();
            }else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not a integer");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
