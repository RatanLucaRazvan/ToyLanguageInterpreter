package Model.statements;

import DataStructures.MyIDictionary;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.types.BoolType;
import Model.types.IntType;
import Model.types.StringType;
import Model.types.Type;
import Model.values.Value;
import PorgramState.PrgState;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;

    public VarDeclStmt(String _name, Type _type){
        name = _name;
        type = _type;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();


        if(symTable.isDefined(name))
        {
            throw new MyException("variable is already declared");
        }
        else
        {
            if(type.equals(new BoolType()))
                symTable.update(name, type.defaultValue());
            else if(type.equals(new IntType()))
                symTable.update(name, type.defaultValue());
            else if(type.equals(new StringType()))
                symTable.update(name, type.defaultValue());
            else
                symTable.update(name, type.defaultValue());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        typeEnv.update(name, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

}
