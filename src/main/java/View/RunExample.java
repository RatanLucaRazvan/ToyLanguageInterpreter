package View;

import Controller.Controller;

public class RunExample extends Command{
    private Controller controller;
    public RunExample(String _key, String _description, Controller _controller) {
        super(_key, _description);
        controller = _controller;
    }

    @Override
    public void execute() {
        try{
            controller.allStep();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Controller getController() {return controller;}
}
