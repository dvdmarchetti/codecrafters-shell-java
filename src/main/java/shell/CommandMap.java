package shell;

import command.Command;
import command.RuntimeCommand;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    private final String[] pathDirectories;
    private final Map<String, Command> commands;

    public CommandMap(String[] pathDirectories) {
        this.pathDirectories = pathDirectories;
        this.commands = new HashMap<>();
    }

    public void register(String key, Class<? extends Command> command) {
        try {
            Constructor<? extends Command> constructor = command.getConstructor(CommandMap.class);
            Command instance = constructor.newInstance(this);
            register(key, instance);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("An error occurred while instantiating command.", e);
        }
    }

    public void register(String key, Command command) {
        this.commands.putIfAbsent(key, command);
    }

    public Command get(String key) {
        if (commands.containsKey(key)) {
            return commands.get(key);
        }

        for (String path : pathDirectories) {
            Path executable = Paths.get(path, key);

            if (executable.toFile().canExecute()) {
                Command command = new RuntimeCommand(executable, this);
                this.register(executable.getFileName().toString(), command);
                return command;
            }
        }

        return null;
    }
}
