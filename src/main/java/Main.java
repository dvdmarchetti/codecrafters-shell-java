import command.Command;
import command.EchoCommand;
import command.ExitCommand;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Command> COMMAND_MAP = Map.of(
            "exit", new ExitCommand(),
            "echo", new EchoCommand()
    );

    public static void main(String[] args) {
        while (true) {
            System.out.print("$ ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String[] parts = input.split(" ");
            String commandKey = parts[0];
            if (! COMMAND_MAP.containsKey(commandKey)) {
                System.out.printf("%s: command not found%n", commandKey);
                continue;
            }

            final String[] commandArgs;
            if (parts.length > 1) {
                commandArgs = Arrays.copyOfRange(parts, 1, parts.length);
            } else {
                commandArgs = null;
            }

            Command command = COMMAND_MAP.get(commandKey);
            command.execute(commandArgs);
        }
    }
}
