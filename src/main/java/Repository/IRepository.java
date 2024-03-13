package Repository;

import Exceptions.MyException;
import PorgramState.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void addState(PrgState newState);

    void logPrgStateExec(PrgState prg) throws MyException, IOException;

    void emptyFile() throws MyException;

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> newList);
}
