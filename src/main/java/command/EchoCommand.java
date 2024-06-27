package command;

import shell.CommandMap;

public class EchoCommand extends Command implements ShellCommand {

    public EchoCommand(CommandMap commandMap) {
        super(commandMap);
    }

    @Override
    public void execute(String... args) {
        System.out.println(String.join(" ", args));
    }

    public String toString() {
        return "echo";
    }
}
