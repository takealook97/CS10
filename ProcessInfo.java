public class ProcessInfo {
    char name;
    int timeProgress;
    int maxTime;
    String status;

    public ProcessInfo(char alphabet, int timeProgress, int maxTime, String status) {
        this.name = alphabet;
        this.timeProgress = timeProgress;
        this.maxTime = maxTime;
        this.status = status;
    }
}
