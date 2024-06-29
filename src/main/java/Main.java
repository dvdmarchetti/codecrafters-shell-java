import shell.Application;

public class Main {
    public static void main(String[] args) {
        String envPath = System.getenv("PATH");
        String bootDir = System.getProperty("user.dir");
        new Application(envPath, bootDir).run();
    }
}
