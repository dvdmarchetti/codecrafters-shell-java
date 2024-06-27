package command;

import shell.CommandMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.file.Path;

public class ExecutableCommand extends Command {
    private final Path path;

    public ExecutableCommand(Path path, CommandMap commandMap) {
        super(commandMap);
        this.path = path;
    }

    @Override
    public void execute(String... args) {
        try {
            Process process = new ProcessBuilder(prepend(path.getFileName().toString(), args)).start();
            InputStream inputStream = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T[] prepend(T item, T[] elements) {
        T[] newArray = (T[]) Array.newInstance(elements.getClass().getComponentType(), elements.length + 1);
        newArray[0] = item;
        System.arraycopy(elements, 0, newArray, 1, elements.length);
        return newArray;
    }

    public String getPath() {
        return this.path.toAbsolutePath().toString();
    }

    public String toString() {
        return path.getFileName().toString();
    }
}
