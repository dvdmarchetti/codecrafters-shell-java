import shell.Application;

public class Main {
    public static void main(String[] args) {
        String envPath = System.getenv("PATH");
        new Application(envPath).run();
    }
}
