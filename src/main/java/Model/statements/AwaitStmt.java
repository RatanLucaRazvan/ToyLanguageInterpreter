package Model.statements;

import DataStructures.MyIBarrierTable;
import DataStructures.MyIDictionary;
import Exceptions.FullCapacityExc;
import Exceptions.MyException;
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

public class AwaitStmt implements IStmt{
    private final String id;
    private static final Lock lock = new ReentrantLock();

    public AwaitStmt(String _id){
        id = _id;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();
        if(symTable.isDefined(id)) {
            if (symTable.lookup(id).getType().equals(new IntType())) {
                IntValue f = (IntValue) symTable.lookup(id);
                int foundIndex = f.getVal();
                if (barrierTable.containsKey(foundIndex)) {
                    Pair<Integer, List<Integer>> foundBarrier = barrierTable.get(foundIndex);
                    int length = foundBarrier.getValue().size();
                    int n = foundBarrier.getKey();
                    ArrayList<Integer> list = (ArrayList<Integer>) foundBarrier.getValue();
                    if (n > length) {
                        if (list.contains(state.getId()))
                            state.getStk().push(this);
                        else {
                            list.add(state.getId());
                            barrierTable.update(foundIndex, new Pair<>(n, list));
//                        state.setBarrierTable(barrierTable);
                        }
                    }
                } else {
                    throw new MyException("Index not in barrier table");
                }
            } else {
                throw new MyException("Var is not of iny type");
            }
        } else {
            throw new MyException("Id is not defined");
        }
        lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException, FullCapacityExc {
        if(typeEnv.lookup(id).equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Variable is not of type int");
    }

    @Override
    public String toString() {
        return "await(" + id + ")";
    }
}

