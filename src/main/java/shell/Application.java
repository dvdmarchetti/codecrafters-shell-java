package shell;

import command.Command;
import command.EchoCommand;
import command.ExitCommand;
import command.TypeCommand;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private final CommandMap commandMap;

    public Application(String envPath) {
        String[] paths = envPath.split(File.pathSeparator);
        this.commandMap = new CommandMap(paths);

        registerShellBuiltin();
    }

    private void registerShellBuiltin() {
        commandMap.register("exit", ExitCommand.class);
        commandMap.register("echo", EchoCommand.class);
        commandMap.register("type", TypeCommand.class);
    }

    public void run() {
        while (true) {
            System.out.print("$ ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            String commandKey = parts[0];
            Command command = commandMap.get(commandKey);
            if (command == null) {
                System.out.printf("%s: command not found%n", commandKey);
                continue;
            }

            final String[] commandArgs;
            if (parts.length > 1) {
                commandArgs = Arrays.copyOfRange(parts, 1, parts.length);
            } else {
                commandArgs = null;
            }
            command.execute(commandArgs);
        }
    }

}
