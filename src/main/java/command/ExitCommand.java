package command;

public class ExitCommand implements Command {

    @Override
    public void execute(String... args) {
        int exitCode = Integer.parseInt(args[0]);
        System.exit(exitCode);
    }

}
