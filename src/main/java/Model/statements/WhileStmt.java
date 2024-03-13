package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import DataStructures.MyIStack;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.BoolType;
import Model.types.Type;
import Model.values.BoolValue;
import Model.values.Value;
import PorgramState.PrgState;

public class WhileStmt implements IStmt{
    Exp exp;

    IStmt stmt;

    public WhileStmt(Exp _exp, IStmt _stmt){
        exp = _exp;
        stmt = _stmt;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stack = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value v = exp.eval(symTbl, heap);
        if (v.getType().equals(new BoolType())) {
            if (((BoolValue) v).getVal()){
                stack.push(this);
                stack.push(stmt);
            }
        }
        else
            throw new MyException("Expression is not boolean type");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType())){
            stmt.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("The condition of while does not have the bool type");
    }

    @Override
    public String toString() {
        return "(while (" + exp.toString() + ")" + stmt.toString() + ")";
    }
}
