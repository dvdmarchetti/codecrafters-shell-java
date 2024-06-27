package command;

import shell.CommandMap;

import java.nio.file.Path;

public class ExecutableCommand extends Command {
    private final Path path;

    public ExecutableCommand(Path path, CommandMap commandMap) {
        super(commandMap);
        this.path = path;
    }

    @Override
    public void execute(String... args) {
        //
    }

    public String getPath() {
        return this.path.toAbsolutePath().toString();
    }
}
