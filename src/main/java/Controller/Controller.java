package Controller;

import DataStructures.MyIHeap;
import Exceptions.MyException;
import Model.values.RefValue;
import Model.values.Value;
import PorgramState.PrgState;
import Repository.IRepository;
import Repository.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;
    ExecutorService executor;

    public Controller(IRepository _repository)
    {
        repository = _repository;
    }


    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapReferencedAddressed, PrgState currProgram){
        MyIHeap<Integer, Value> heap = currProgram.getHeap();
        return heap.getContent().entrySet().stream()
                .filter(elem -> symTableAddr.contains(elem.getKey()) || heapReferencedAddressed.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromHeap(Map<Integer, Value> heap){
        return heap.values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v ->{RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void allStep() throws Exception {
        repository.emptyFile();
        executor = Executors.newFixedThreadPool(2);
        // remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while(prgList.size() > 0){
            Collection<Value> addresses = prgList.stream()
                            .flatMap(program -> program.getSymTable().getContent().values().stream())
                            .toList();
            prgList.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddrFromSymTable(addresses), this.getAddressesFromHeap(prgList.get(0).getHeap().getContent()), prgList.get(0)));

            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repository.getPrgList());
        }

        executor.shutdownNow();
        // HERE the repo still contains at least one Completed Prg
        // and its List<PrgState? is not empty. Note that oneStepFroAllPRg calls the method
        // setPrgList of repository in order to change the repository

        //update the repository state
        repository.setPrgList(prgList);
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws Exception {
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .toList();

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(executor.toString());
                    }
                    return null;
                })
                .filter(p -> p!=null)
                .toList();
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        repository.setPrgList(prgList);
    }

    public void oneStepExecution(){
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> programs = removeCompletedPrg(this.repository.getPrgList());

        if(programs.size() > 0){
            Collection<Value> addresses = programs.stream().
                    flatMap(program -> program.getSymTable().getContent().values().stream())
                    .collect(Collectors.toList());

            programs.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddrFromSymTable(addresses), this.getAddressesFromHeap(programs.get(0).getHeap().getContent()), programs.get(0)));

            try{
                this.oneStepForAllPrg(programs);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            programs = removeCompletedPrg(this.repository.getPrgList());
        }
        executor.shutdown();
        this.repository.setPrgList(programs);
    }

    public IRepository getRepository() {return repository;}
}
