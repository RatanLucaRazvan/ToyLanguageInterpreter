package org.example.a7;

import Controller.Controller;
import DataStructures.*;
import Model.expressions.*;
import Model.statements.*;
import Model.types.*;
import Model.values.BoolValue;
import Model.values.IntValue;
import Model.values.StringValue;
import Model.values.Value;
import PorgramState.PrgState;
import Repository.IRepository;
import Repository.Repository;
import View.RunExample;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.util.List;

public class ProgramController {
    @FXML
    private ListView<RunExample> programsList;

    public ListView<RunExample> getExamplesListView(){
        return this.programsList;
    }

    @FXML
    public void initialize() throws Exception {

        try {
            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))), new PrintStmt(new VarExp("v"))));
            MyIDictionary<String, Type> typeEnv1 = new MyDictionary<String, Type>();
            ex1.typecheck(typeEnv1);
            PrgState prg1 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), new MyFileTable<StringValue, BufferedReader>(), ex1, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository1 = new Repository(prg1, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log1.txt");
            Controller controller1 = new Controller(repository1);
            this.programsList.getItems().add(new RunExample("1", ex1.toString(), controller1));
        }
        catch(Exception e){
            System.out.println("1: " + e.getMessage());
        }

        try {
            IStmt ex2 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                    new CompStmt(new OpenReadFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                            new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseReadFile(new VarExp("varf"))))))))));
            MyIDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
            ex2.typecheck(typeEnv2);
            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex2, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository2 = new Repository(prg2, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log2.txt");
            Controller controller2 = new Controller(repository2);
            this.programsList.getItems().add(new RunExample("2", ex2.toString(), controller2));
        }
        catch(Exception e){
            System.out.println("2: " + e.getMessage());
        }


        try {
            IStmt ex3 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp("+", new ValueExp(new IntValue(2)), new
                                    ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new ValueExp(new
                                            IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            MyIDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
            ex3.typecheck(typeEnv3);
            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex3, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository3 = new Repository(prg3, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log3.txt");
            Controller controller3 = new Controller(repository3);
            this.programsList.getItems().add(new RunExample("3", ex3.toString(), controller3));
        }
        catch(Exception e){
            System.out.println("3: " + e.getMessage());
        }

        try {
            IStmt ex4 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                            IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                            VarExp("v"))))));
            MyIDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
            ex4.typecheck(typeEnv4);
            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex4, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository4 = new Repository(prg4, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log4.txt");
            Controller controller4 = new Controller(repository4);
            this.programsList.getItems().add(new RunExample("4", ex4.toString(), controller4));
        }
        catch(Exception e){
            System.out.println("4: " + e.getMessage());
        }

        try {
            IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp("*", new ValueExp(new IntValue(4)), new ValueExp(new IntValue(5))))
                    , new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(15))), new IfStmt(new RelationalExp(new VarExp("a"), new VarExp("b"), ">"), new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b")))))));
            MyIDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
            ex5.typecheck(typeEnv5);
            PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex5, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository5 = new Repository(prg5, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log5.txt");
            Controller controller5 = new Controller(repository5);
            this.programsList.getItems().add(new RunExample("5", ex5.toString(), controller5));
        }
        catch(Exception e){
            System.out.println("5: " + e.getMessage());
        }

        try {
            IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new AllocStmt("a", new VarExp("v")),
                            new CompStmt(new PrintStmt(new ReadHExp(new VarExp("v"))), new PrintStmt(new ArithExp("+", new ReadHExp(new ReadHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
            MyIDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
            ex6.typecheck(typeEnv6);
            PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex6, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository6 = new Repository(prg6, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log6.txt");
            Controller controller6 = new Controller(repository6);
            this.programsList.getItems().add(new RunExample("6", ex6.toString(), controller6));
        }
        catch (Exception e){
            System.out.println("6: " + e.getMessage());
        }


        try {
            IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new AllocStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
            MyIDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
            ex7.typecheck(typeEnv7);
            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex7, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository7 = new Repository(prg7, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log7.txt");
            Controller controller7 = new Controller(repository7);
            this.programsList.getItems().add(new RunExample("7", ex7.toString(), controller7));
        }
        catch(Exception e){
            System.out.println("7: " + e.getMessage());
        }



        try {
            IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new ReadHExp(new VarExp("v"))),
                    new CompStmt(new HeapW("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp("+", new ReadHExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
            MyIDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
            ex8.typecheck(typeEnv8);
            PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex8, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository8 = new Repository(prg8, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log8.txt");
            Controller controller8 = new Controller(repository8);
            this.programsList.getItems().add(new RunExample("8", ex8.toString(), controller8));
        }
        catch(Exception e){
            System.out.println("8: " + e.getMessage());
        }


        try {
            IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                    new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))))), new PrintStmt(new VarExp("v")))));
            MyIDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
            ex9.typecheck(typeEnv9);
            PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex9, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository9 = new Repository(prg9, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log9.txt");
            Controller controller9 = new Controller(repository9);
            this.programsList.getItems().add(new RunExample("9", ex9.toString(), controller9));
        }
        catch(Exception e){
            System.out.println("9: " + e.getMessage());
        }


        try {
            IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(19))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                    new CompStmt(new AllocStmt("a", new VarExp("v")), new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ReadHExp(new ReadHExp(new VarExp("a")))))))));
            MyIDictionary<String, Type> typeEnv10 = new MyDictionary<String, Type>();
            ex10.typecheck(typeEnv10);
            PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex10, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository10 = new Repository(prg10, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log10.txt");
            Controller controller10 = new Controller(repository10);
            this.programsList.getItems().add(new RunExample("10", ex10.toString(), controller10));
        }
        catch(Exception e){
            System.out.println("10: " + e.getMessage());
        }


        try {
            IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new AllocStmt("a", new ValueExp(new IntValue(22))),
                            new CompStmt(new ForkStmt(new CompStmt(new HeapW("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHExp(new VarExp("a")))))))));
            MyIDictionary<String, Type> typeEnv11 = new MyDictionary<String, Type>();
            ex11.typecheck(typeEnv11);
            PrgState prg11 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), ex11, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository11 = new Repository(prg11, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log11.txt");
            Controller controller11 = new Controller(repository11);
            this.programsList.getItems().add(new RunExample("11", ex11.toString(), controller11));
        }
        catch(Exception e){
            System.out.println("11: " + e.getMessage());
        }

        try {
            IStmt ex12 = new CompStmt(
                    new VarDeclStmt("v1", new RefType(new IntType())),
                    new CompStmt(
                            new VarDeclStmt("v2", new RefType(new IntType())),
                            new CompStmt(
                                    new VarDeclStmt("v3", new RefType(new IntType())),
                                    new CompStmt(
                                            new VarDeclStmt("cnt", new IntType()),
                                            new CompStmt(
                                                    new AllocStmt("v1", new ValueExp(new IntValue(2))),
                                                    new CompStmt(
                                                            new AllocStmt("v2", new ValueExp(new IntValue(3))),
                                                            new CompStmt(
                                                                    new AllocStmt("v3", new ValueExp(new IntValue(4))),
                                                                    new CompStmt(
                                                                            new NewBarrierStmt("cnt", new ReadHExp(new VarExp("v2"))),
                                                                            new CompStmt(
                                                                                    new ForkStmt(
                                                                                            new CompStmt(
                                                                                                    new AwaitStmt("cnt"),
                                                                                                    new CompStmt(
                                                                                                            new HeapW("v1", new ArithExp("*", new ReadHExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                                                                                            new PrintStmt(new ReadHExp(new VarExp("v1")))
                                                                                                    )
                                                                                            )
                                                                                    ),
                                                                                    new CompStmt(
                                                                                            new ForkStmt(
                                                                                                    new CompStmt(
                                                                                                            new AwaitStmt("cnt"),
                                                                                                            new CompStmt(
                                                                                                                    new HeapW("v2", new ArithExp("*", new ReadHExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                                                                                    new CompStmt(
                                                                                                                            new HeapW("v2", new ArithExp("*", new ReadHExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                                                                                            new PrintStmt(new ReadHExp(new VarExp("v2")))
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            ),
                                                                                            new CompStmt(
                                                                                                    new AwaitStmt("cnt"),
                                                                                                    new PrintStmt(new ReadHExp(new VarExp("v3")))
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );
            MyIDictionary<String, Type> typeEnv12 = new MyDictionary<String, Type>();
            ex12.typecheck(typeEnv12);
            PrgState prg12 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), new MyFileTable<StringValue, BufferedReader>(), ex12, new MyHeap<Integer, Value>(), new MyBarrierTable<Integer, Pair<Integer, List<Integer>>>(), PrgState.newId());
            IRepository repository12 = new Repository(prg12, "D:\\UBB\\MAP\\JavaFinalProject" +
                    "\\log12.txt");
            Controller controller12 = new Controller(repository12);
            this.programsList.getItems().add(new RunExample("12", ex12.toString(), controller12));
        }
        catch(Exception e){
            System.out.println("12: " + e.getMessage());
        }


        this.programsList.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunExample>() {
            @Override
            public String toString(RunExample runExample) {
                return runExample.getDescription();
            }

            @Override
            public RunExample fromString(String s) {
                return null;
            }
        }));

        this.programsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
