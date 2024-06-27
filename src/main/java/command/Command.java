package command;

import shell.CommandMap;

public abstract class Command {

    protected final CommandMap commandMap;

    public Command(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    public abstract void execute(String... args);

}
