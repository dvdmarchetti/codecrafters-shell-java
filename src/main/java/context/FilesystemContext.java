package context;

import lombok.Builder;
import lombok.Getter;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesystemContext {
    private static final String HOME_CHAR = "~";

    private final String homeDirectory;
    @Getter
    private Path currentPath;

    public FilesystemContext(String bootDirectory, String homeDirectory) {
        this.homeDirectory = homeDirectory;
        this.currentPath = Paths.get(bootDirectory);
    }

    @Builder
    public static FilesystemContext create(String bootDirectory, String homeDirectory) {
        return new FilesystemContext(bootDirectory, homeDirectory);
    }

    public void navigate(String target) throws NoSuchFileException {
        if (target.contains(HOME_CHAR)) {
            target = target.replace(HOME_CHAR, homeDirectory);
        }

        Path targetDirectory = this.currentPath.resolve(target);
        if (! targetDirectory.toFile().exists()) {
            throw new NoSuchFileException(target);
        }

        this.currentPath = targetDirectory.normalize();
    }
}
