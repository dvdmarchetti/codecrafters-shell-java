package shell;

import command.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Application {
    private final CommandMap commandMap;

    public Application(String envPath) {
        this.commandMap = new CommandMap();

        registerShellBuiltin();
        discoverExecutables(envPath);
    }

    private void registerShellBuiltin() {
        commandMap.register("exit", ExitCommand.class);
        commandMap.register("echo", EchoCommand.class);
        commandMap.register("type", TypeCommand.class);
    }

    private void discoverExecutables(String envPath) {
        String[] paths = envPath.split(File.pathSeparator);

        for (String path : paths) {
            try (Stream<Path> walk = Files.list(Paths.get(path))) {
                walk.filter(Files::isRegularFile)
                        .filter(p -> p.toFile().canExecute())
                        .forEach(file -> {
                            Command command = new ExecutableCommand(file, commandMap);
                            commandMap.register(file.getFileName().toString(), command);
                        });
            } catch (IOException e) {
                // Skip invalid folder. No prints as output is verified
            }
        }
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
