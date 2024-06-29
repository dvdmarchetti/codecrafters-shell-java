package command;

import context.FilesystemContext;
import shell.CommandMap;

public class PwdCommand extends Command implements BuiltinCommand {
    private final FilesystemContext context;

    public PwdCommand(CommandMap commandMap, FilesystemContext context) {
        super(commandMap);
        this.context = context;
    }

    @Override
    public void execute(String... args) {
        System.out.println(this.context.getCurrentPath().toAbsolutePath());
    }
}
