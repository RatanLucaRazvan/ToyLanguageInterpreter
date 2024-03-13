package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import DataStructures.MyIStack;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Exceptions.VariableNotDefinedExc;
import Model.expressions.Exp;
import Model.types.IntType;
import Model.types.StringType;
import Model.types.Type;
import Model.values.IntValue;
import Model.values.StringValue;
import Model.values.Value;
import PorgramState.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{
    Exp exp;
    String varName;

    public ReadFileStmt(Exp _exp, String _varName){
        exp = _exp;
        varName = _varName;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stack = state.getStk();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        
        if(symTable.isDefined(varName))
        {
            Value value = symTable.get(varName);
            if(value.getType().equals(new IntType())){
                Value val = exp.eval(symTable, heap);
                if(fileTable.isDefined((StringValue) val)){
                    try{
                        BufferedReader buffer = fileTable.get((StringValue) val);
                        String line = buffer.readLine();
                        if(line == null){
                            symTable.update(varName, new IntValue(0));
                        }
                        else{
                            try{
                                symTable.update(varName, new IntValue(Integer.parseInt(line)));
                            } catch(Exception ignored){
                                throw new MyException("Cannot read value because EOF has been reached!\n");
                            }
                        }
                    }
                    catch(IOException exc){
                        throw new IOException("Error on read!");
                    }
                }
                else
                    throw new MyException("The file path is not defined in the file table!\n");
            }
            else
                throw new MyException("Variable does not have the correct type");
        }
        else
            throw new VariableNotDefinedExc("Variable not defined");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        Type typexp = exp.typecheck(typeEnv);
        Type typevar = typeEnv.lookup(varName);
        if(typevar.equals(new IntType())){
            if(typexp.equals(new StringType()))
                return typeEnv;
            else
                throw new MyException("File path must be a string");
        }
        else
            throw new MyException("Variable is not an integer!");
    }

    @Override
    public String toString() {
        return "readFile(" + exp.toString() + "," + varName + ")";
    }
}
