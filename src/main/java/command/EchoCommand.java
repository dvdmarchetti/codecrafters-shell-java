package command;

import shell.CommandMap;

public class EchoCommand extends Command {

    public EchoCommand(CommandMap commandMap) {
        super(commandMap);
    }

    @Override
    public void execute(String... args) {
        System.out.println(String.join(" ", args));
    }

}
