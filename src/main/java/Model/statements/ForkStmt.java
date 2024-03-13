package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIStack;
import DataStructures.MyStack;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.types.Type;
import Model.values.Value;
import PorgramState.PrgState;

public class ForkStmt implements IStmt{
    IStmt stmt;

    public ForkStmt(IStmt _stmt){
        stmt = _stmt;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, Value> newDictionary = symTable.getCopy();

        return new PrgState(stack, newDictionary, state.getOut(), state.getFileTable(), this.stmt, state.getHeap(), state.getBarrierTable(), PrgState.newId());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        stmt.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}
