package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import DataStructures.MyIStack;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.StringType;
import Model.types.Type;
import Model.values.StringValue;
import Model.values.Value;
import PorgramState.PrgState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStmt implements IStmt{
    Exp exp;


    public OpenReadFileStmt(Exp _exp){
        exp = _exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {

        MyIStack<IStmt> stack = state.getStk();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if(exp.eval(symTable, heap).getType().equals(new StringType())){
            if(fileTable.isDefined((StringValue) exp.eval(symTable, heap)))
            {
                throw new Exception("The filepath is already a key in the FileTable!");
            }
            else
            {
                try {
                    Value filePath = exp.eval(symTable, heap);
                    BufferedReader reader = new BufferedReader(new FileReader(((StringValue) filePath).getVal()));
                    fileTable.update((StringValue) filePath, reader);

//                    System.out.println("FILE UPDATE\n" + fileTable.transformString());
                }
                catch (FileNotFoundException ex)
                {
                    throw  new MyException(ex.getMessage());
                }
            }
        }
        else {
            throw new MyException("Statement not correct!" + exp.toString());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new StringType())){
            return typeEnv;
        }
        else
            throw new MyException("Incorrect type for open statement");
    }

    @Override
    public String toString() {
        return "openFile(" + exp.toString() + ")";
    }
}
