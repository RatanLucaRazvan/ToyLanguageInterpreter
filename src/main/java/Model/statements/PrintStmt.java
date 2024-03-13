package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import DataStructures.MyIList;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.Type;
import Model.values.Value;
import PorgramState.PrgState;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp _exp){
        exp = _exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        out.add(exp.eval(symTable, heap));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" +exp.toString()+ ")";
    }
}
