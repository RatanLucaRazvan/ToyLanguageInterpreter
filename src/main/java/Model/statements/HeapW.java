package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.RefType;
import Model.types.Type;
import Model.values.RefValue;
import Model.values.Value;
import PorgramState.PrgState;

public class HeapW implements IStmt{
    String var;
    Exp exp;

    public HeapW(String _var, Exp _exp){
        var = _var;
        exp = _exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(var)){
            Value val = symTbl.lookup(var);
            if(val.getType().equals(new RefType(((RefValue)val).getLocationType()))){
                int address = ((RefValue) val).getAddress();
                if(heap.isDefined(address)){
                    Value evaluation = exp.eval(symTbl, heap);
                    if(evaluation.getType().equals(((RefValue)val).getLocationType())){
                        heap.update(address, evaluation);
                    }
                    else
                        throw new MyException("Types not equal!");
                }
                else
                    throw new MyException("Not defined in heap!");
            }
            else
                throw new MyException("Incorrect type!");
        }
        else
            throw new MyException("Variable not defined!");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        Type typevar = typeEnv.lookup(var);
        Type typexp = exp.typecheck(typeEnv);
        if(typevar.equals(new RefType(typexp))){
            return typeEnv;
        }
        else
            throw new MyException("Incorrect type for heap write statement");
    }

    @Override
    public String toString() {
        return "wH(" + var + ", " + exp.toString() + ")";
    }
}
