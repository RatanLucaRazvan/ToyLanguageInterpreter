package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import DataStructures.MyIStack;
import Exceptions.MyException;
import Exceptions.VariableNotDefinedExc;
import Model.expressions.Exp;
import Model.types.Type;
import Model.values.Value;
import PorgramState.PrgState;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String _id, Exp _exp){
        id = _id;
        exp = _exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl, heap);
            Type typId = (symTbl.lookup(id)).getType();
            if(val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable " + id + " and type of the assigned expression do not match");
        }
        else
            throw new VariableNotDefinedExc("The used variable "+ id +" was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if(typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return id + " = " + exp.toString();
    }
}
