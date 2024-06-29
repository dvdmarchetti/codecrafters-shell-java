package context;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesystemContext {
    private String bootPath;

    @Getter
    private Path currentPath;

    public FilesystemContext(String bootPath) {
        this.bootPath = bootPath;
        this.currentPath = Paths.get(bootPath);
    }
}
