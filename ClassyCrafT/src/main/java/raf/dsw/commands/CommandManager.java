package raf.dsw.commands;

import lombok.Getter;
import raf.dsw.core.ApplicationFramework;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<>();
    private int currCommand = 0;

    public void addCommand(AbstractCommand command){
        while (currCommand < commands.size())
            commands.remove(currCommand);
        commands.add(command);
        doCommand();
    }

    public void doCommand(){
        if (currCommand < commands.size()){
            commands.get(currCommand++).doCommand();;
            ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
    }

    public void undoCommand(){
        if (currCommand > 0){
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currCommand).undoCommand();
        }
    }
}
