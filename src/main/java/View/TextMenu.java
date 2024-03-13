package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu(){
        commands = new HashMap<>();
    }

    public void addCommand(Command c){
        commands.put(c.getKey(), c);
    }

    public void printMenu(){
        for(Command com : commands.values()){
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> executed = new ArrayList<>();
        while(true){
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if(executed.contains(key))
            {
                System.out.println("Already executed");
                continue;
            }
            if(com == null){
                System.out.println("Invalid Option");
                continue;
            }
            executed.add(key);
            com.execute();
        }
    }
}
