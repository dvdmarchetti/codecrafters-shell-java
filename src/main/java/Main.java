import shell.Application;

public class Main {
    public static void main(String[] args) {
        String envPath = System.getenv("PATH");
        String bootDir = System.getProperty("user.dir");
        String homeDir = System.getenv("HOME");
        new Application(envPath, bootDir, homeDir).run();
    }
}
