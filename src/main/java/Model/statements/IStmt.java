package Model.statements;

import DataStructures.MyIDictionary;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.types.Type;
import PorgramState.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws Exception;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc;
}
