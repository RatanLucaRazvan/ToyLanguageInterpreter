package Model.statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import DataStructures.MyIStack;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.BoolType;
import Model.types.Type;
import Model.values.BoolValue;
import Model.values.Value;
import PorgramState.PrgState;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> execStack = state.getStk();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value cond = exp.eval(symTable, heap);
        if(!cond.getType().equals(new BoolType()))
            throw new MyException("conditional expression is not a boolean");
        else
            if(((BoolValue)cond).getVal()) {
                execStack.push(thenS);
            } else{
                execStack.push(elseS);
            }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType())){
            thenS.typecheck(typeEnv.getCopy());
            elseS.typecheck(typeEnv.getCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool " + exp);
    }
}
