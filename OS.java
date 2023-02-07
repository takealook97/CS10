public class OS {
    public static void main(String[] args) {
        Output output = new Output();
        output.printInit();
        Process process = new Process();
        process.initialize();
        output.printReady();
        output.printProcess();
        output.printEnd();
    }
}
