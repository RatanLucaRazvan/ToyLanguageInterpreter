package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.StringType;
import Model.types.Type;
import Model.values.StringValue;
import Model.values.Value;
import PorgramState.PrgState;

import java.io.BufferedReader;

public class CloseReadFile implements IStmt{
    Exp exp;

    public CloseReadFile(Exp _exp){
        exp = _exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value value = exp.eval(symTable, heap);
        if(fileTable.isDefined((StringValue) value)){
            BufferedReader buffer = fileTable.get((StringValue) value);
            buffer.close();
            fileTable.remove((StringValue) value);
        }
        else
            throw new MyException("File path not defined!");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new StringType())){
            return typeEnv;
        }
        else
            throw new MyException("Invalid type for close statement");
    }

    @Override
    public String toString() {
        return "closeFile(" + exp.toString() + ")";
    }
}
