package PorgramState;

import DataStructures.*;
import Exceptions.FullCapacityExc;
import Exceptions.NoStatementsExc;
import Model.statements.IStmt;
import Model.values.StringValue;
import Model.values.Value;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;

    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIList<Value> out;

    MyIHeap<Integer, Value> heap;

    MyIBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable;
    IStmt originalProgram;

    int id;
    private static int nextId = 0;

    public static synchronized int newId(){
        return nextId++;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> _fileTable, IStmt prg, MyIHeap<Integer, Value> _heap, MyIBarrierTable<Integer, Pair<Integer, List<Integer>>> _barrierTable, int _id) throws FullCapacityExc {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = _fileTable;
        barrierTable = _barrierTable;
        originalProgram = prg;
        heap = _heap;

        stk.push(prg);
        id = _id;
    }

    public MyIStack<IStmt> getStk(){
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable(){
        return symTable;
    }

    public MyIList<Value> getOut() {return out;};

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {return fileTable;}

    public MyIHeap<Integer, Value> getHeap() { return heap;}

    public MyIBarrierTable<Integer, Pair<Integer, List<Integer>>> getBarrierTable() {return barrierTable;}

    public int getId() {return id;}


    @Override
    public String toString() {
        return "Id: " + id + "\nExeStack:\n" + exeStack.transformString() + "\nSymTable:\n" + symTable.transformString() + "\n\nOut\n" + out.transformString() + "\nFileTable\n" + fileTable.transformString() + "\n\nHeap\n" + heap.transformString() + "\n\nBarrier Table\n" + barrierTable.transformString() + "\n----------------------------";
    }

    public PrgState oneStep() throws Exception {
        if(exeStack.isEmpty()) throw new NoStatementsExc("No statements left to be executed");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public boolean isNotCompleted(){
        if(exeStack.isEmpty())
            return false;
        return true;
    }
}
