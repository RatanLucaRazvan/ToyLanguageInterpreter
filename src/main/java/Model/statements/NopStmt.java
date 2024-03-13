package Model.statements;

import DataStructures.MyIDictionary;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.types.Type;
import PorgramState.PrgState;

public class NopStmt implements IStmt{

    public NopStmt(){
    }

    @Override
    public String toString() {
        return "No statement";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception{
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        return typeEnv;
    }
}
