package shell;

import command.*;
import context.FilesystemContext;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private final CommandMap commandMap;
    private final FilesystemContext context;

    public Application(String envPath, String bootDir, String homeDir) {
        String[] paths = envPath.split(File.pathSeparator);
        this.commandMap = new CommandMap(paths);
        this.context = FilesystemContext.builder()
                .bootDirectory(bootDir)
                .homeDirectory(homeDir)
                .build();

        registerShellBuiltin();
    }

    private void registerShellBuiltin() {
        commandMap.register("exit", ExitCommand.class);
        commandMap.register("echo", EchoCommand.class);
        commandMap.register("type", TypeCommand.class);
        commandMap.register("pwd", new PwdCommand(commandMap, context));
        commandMap.register("cd", new CdCommand(commandMap, context));
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
