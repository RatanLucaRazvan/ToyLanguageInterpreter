package org.example.a7;

import DTOs.HeapEntry;
import DTOs.SymTableEntry;
import DataStructures.*;
import Exceptions.MyException;
import Model.statements.IStmt;
import Model.values.StringValue;
import Model.values.Value;
import PorgramState.PrgState;
import View.RunExample;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainController {
    private ProgramController programController;
    private PrgState selectedProgram;

    private PrgState selectedProgramInList;
//    private RunExample selectedExample;


    @FXML
    private ListView<Value> outList;

    @FXML
    private ListView<StringValue> fileTable;

    @FXML
    private ListView<PrgState> prgStatesIds;

    @FXML
    private ListView<IStmt> exeStack;

    @FXML
    private TableView<HeapEntry> heapTable;

    @FXML
    private TableColumn<HeapEntry, String> addressColumn;

    @FXML
    private TableColumn<HeapEntry, String> valueHeapColumn;

    @FXML
    private TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> barrierTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> indexColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> valueColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, String> listColumn;

    @FXML
    private TableView <SymTableEntry> symTable;
    @FXML
    private TableColumn<SymTableEntry, String> variableColumn;
    @FXML
    private TableColumn<SymTableEntry, String> valueSymColumn;

    @FXML
    private Button runButton;

    @FXML
    private TextField prgStatesNr;



    public void setProgramController(ProgramController _programController){
        programController = _programController;

        this.programController.getExamplesListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex) -> {try {
            ex.getController().getRepository().emptyFile();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
            this.showDataForSelectedExample(ex); this.showDataForSelectedProgramState(ex.getController().getRepository().getPrgList().get(0)); selectedProgramInList = ex.getController().getRepository().getPrgList().get(0);});
    }

    @FXML
    private void initialize(){
        this.prgStatesNr.setEditable(false);

        this.addressColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapAddress"));
        this.valueHeapColumn. setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapValue"));

        this.variableColumn.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("variableName"));
        this.valueSymColumn.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("value"));

        indexColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue().getKey()).asObject());
        listColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getValue().toString()));


        this.outList.setCellFactory(TextFieldListCell.forListView(new StringConverter<Value>() {
            @Override
            public String toString(Value value) {
                return value.toString();
            }

            @Override
            public Value fromString(String s) {
                return null;
            }
        }));

        this.fileTable.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        this.prgStatesIds.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public String toString(PrgState prgState) {
                return Integer.toString(prgState.getId());
            }

            @Override
            public PrgState fromString(String s) {
                return null;
            }
        }));

        this.exeStack.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public String toString(IStmt iStmt) {
                return iStmt.toString();
            }

            @Override
            public IStmt fromString(String s) {
                return null;
            }
        }));

        this.prgStatesIds.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.prgStatesIds.getSelectionModel().selectedItemProperty().addListener((a,b,state) -> {
            if(state != null) {
                showDataForSelectedProgramState(state);
                selectedProgramInList = state;
            }
        });

        this.runButton.setOnAction(actionEvent -> runOneStep(this.programController.getExamplesListView().getSelectionModel().getSelectedItems().get(0)));
    }

    private void showDataForSelectedExample(RunExample example){
        this.heapTable.getItems().clear();
        this.outList.getItems().clear();
        this.fileTable.getItems().clear();
        this.prgStatesIds.getItems().clear();
        this.symTable.getItems().clear();
        this.exeStack.getItems().clear();

        List<PrgState> programStates = example.getController().getRepository().getPrgList();

        if(programStates.size() != 0)
            this.selectedProgram = programStates.get(0);

//        System.out.println(selectedProgram);

        MyIHeap<Integer, Value> sharedHeap = this.selectedProgram.getHeap();
        MyIDictionary<StringValue, BufferedReader> filetable = this.selectedProgram.getFileTable();
        MyIList<Value> out = this.selectedProgram.getOut();
        MyIBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = selectedProgram.getBarrierTable();

        List<Map.Entry<Integer, javafx.util.Pair<Integer, List<Integer>>>> barrierList = new ArrayList<>();
        for (Map.Entry<Integer, javafx.util.Pair<Integer, List<Integer>>> entry: barrierTable.getBarrierTable().entrySet()) {
            barrierList.add(entry);
        }
        barrierTableView.setItems(FXCollections.observableArrayList(barrierList));
        barrierTableView.refresh();



        sharedHeap.getContent().forEach((address, value) -> this.heapTable.getItems().add(new HeapEntry(address, value)));
//        System.out.println(filetable.transformString());
        filetable.getContent().forEach((fileName, filePath) -> this.fileTable.getItems().add(fileName));
        out.getContent().forEach((value) -> this.outList.getItems().add(value));

        programStates.forEach((programState) -> this.prgStatesIds.getItems().add(programState));
        if(programStates.isEmpty()){
            selectedProgramInList = null;
        }

        this.prgStatesNr.setText(Integer.toString(programStates.size()));
//        showDataForSelectedProgramState(selectedProgramInList);

    }

    private void showDataForSelectedProgramState(PrgState program){
        if(program != null) {
            this.symTable.getItems().clear();
            this.exeStack.getItems().clear();

            MyIStack<IStmt> stack = program.getStk();
            MyIDictionary<String, Value> symtable = program.getSymTable();

            stack.getContent().forEach((statement) -> this.exeStack.getItems().add(statement));
            symtable.getContent().forEach((name, value) -> this.symTable.getItems().add(new SymTableEntry(name, value)));
        }
    }

    private void runOneStep(RunExample ex){
        try{
            ex.getController().oneStepExecution();
        }
        catch(Exception e){

        }
        showDataForSelectedExample(ex);
        showDataForSelectedProgramState(selectedProgramInList);
    }


}