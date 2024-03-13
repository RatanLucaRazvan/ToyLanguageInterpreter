package Repository;

import Exceptions.MyException;
import PorgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    String logFilePath;
    List<PrgState> prgStatesList;

    public Repository(PrgState prg, String _logFilePath)
    {
        prgStatesList = new ArrayList<PrgState>();
        prgStatesList.add(prg);
        logFilePath = _logFilePath;
    }

    @Override
    public void addState(PrgState newState){
        prgStatesList.add(newState);
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException, IOException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(prg.toString());
            logFile.close();
        }
        catch(IOException exception)
        {
            throw new MyException("Error on open file!");
        }
    }

    @Override
    public void emptyFile() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            logFile.append("");
            logFile.close();
        }
        catch(IOException exception)
        {
            throw new MyException("Error on open file!");
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return prgStatesList;
    }

    @Override
    public void setPrgList(List<PrgState> newList) {
        prgStatesList = newList;
    }
}
