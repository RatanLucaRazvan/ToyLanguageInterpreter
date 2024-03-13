package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIStack;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.types.Type;
import PorgramState.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt _first, IStmt _snd){
        first = _first;
        snd = _snd;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception{
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        return snd.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return first.toString() + "; " + snd.toString();
    }
}
