package Model.statements;

import DataStructures.MyIBarrierTable;
import DataStructures.MyIDictionary;
import DataStructures.MyIHeap;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
import Model.expressions.Exp;
import Model.types.IntType;
import Model.types.Type;
import Model.values.IntValue;
import Model.values.Value;
import PorgramState.PrgState;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStmt implements  IStmt{
    private final String id;
    private final Exp exp;
    private static final Lock lock = new ReentrantLock();

    public NewBarrierStmt(String _id, Exp _exp){
        id = _id;
        exp = _exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyIBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();
        if(exp.eval(symTable, heap).getType().equals(new IntType())) {
            IntValue number = (IntValue) (exp.eval(symTable, heap));
            int nr = number.getVal();
            int freeAddress = barrierTable.getFreeAddress();
            barrierTable.put(freeAddress, new Pair<>(nr, new ArrayList<>()));
            if (symTable.isDefined(id)) {
                symTable.update(id, new IntValue(freeAddress));
            } else
                throw new MyException(id + " is not defined in the symbol table");
        } else {
            throw new MyException("Expression is not of int type");
        }
        lock.unlock();;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        if(typeEnv.lookup(id).equals(new IntType()))
            if(exp.typecheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("Exp is not of type int");
        else
            throw new MyException("Variable is not of type int");

    }

    @Override
    public String toString() {
        return "newBarrier(" + id + ", " + exp + ")";
    }
}

