package command;

import shell.CommandMap;

public class ExitCommand extends Command implements BuiltinCommand {

    public ExitCommand(CommandMap commandMap) {
        super(commandMap);
    }

    @Override
    public void execute(String... args) {
        int exitCode = Integer.parseInt(args[0]);
        System.exit(exitCode);
    }

    public String toString() {
        return "exit";
    }

}
