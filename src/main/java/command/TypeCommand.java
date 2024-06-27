package command;

import shell.CommandMap;

public class TypeCommand extends Command implements ShellCommand {

    public TypeCommand(CommandMap commandMap) {
        super(commandMap);
    }

    @Override
    public void execute(String... args) {
        String commandKey = args[0];
        Command command = commandMap.get(commandKey);

        if (command == null) {
            System.out.printf("%s: not found%n", commandKey);
        } else if (command instanceof ExecutableCommand exe) {
            System.out.printf("%s is %s%n", commandKey, exe.getPath());
        } else {
            System.out.printf("%s is a shell builtin%n", commandKey);
        }
    }

}
