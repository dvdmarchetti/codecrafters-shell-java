package command;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EchoCommand implements Command {

    @Override
    public void execute(String... args) {
        System.out.println(String.join(" ", args));
    }

}
