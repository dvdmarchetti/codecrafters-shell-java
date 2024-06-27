package shell;

import command.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    private final Map<String, Command> commands;

    public CommandMap() {
        this.commands = new HashMap<>();
    }

    public void register(String key, Class<? extends Command> command) {
        try {
            Constructor<? extends Command> constructor = command.getConstructor(CommandMap.class);
            Command instance = constructor.newInstance(this);
            this.commands.putIfAbsent(key, instance);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("An error occurred while instantiating command.", e);
        }
    }

    public Command get(String key) {
        return commands.getOrDefault(key, null);
    }
}
