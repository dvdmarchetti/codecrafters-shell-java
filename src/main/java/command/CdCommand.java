package command;

import context.FilesystemContext;
import shell.CommandMap;

import java.nio.file.NoSuchFileException;

public class CdCommand extends Command {
    private final FilesystemContext context;

    public CdCommand(CommandMap commandMap, FilesystemContext context) {
        super(commandMap);
        this.context = context;
    }

    @Override
    public void execute(String... args) {
        try {
            this.context.navigate(args[0]);
        } catch (NoSuchFileException e) {
            System.out.printf("cd: %s: No such file or directory%n", args[0]);
        }
    }
}
