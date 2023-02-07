public class Processing {
    String[] status = new String[3];
    static int[] time = new int[3];
    static int[] left = new int[3];

    void processing() {
        while (true) {
            if (time[0] == time[2] && time[0] < Process.processMap.get(Process.process[0])) {
                time[0]++;
                getStatus(Process.process[0]);
                if (!status[1].equals("terminated")) left[1]++;
                if (!status[2].equals("terminated")) left[2]++;
                break;
            } else if (time[1] == time[2] && time[1] < Process.processMap.get(Process.process[1])) {
                time[1]++;
                getStatus(Process.process[1]);
                if (!status[0].equals("terminated")) left[0]++;
                if (!status[2].equals("terminated")) left[2]++;
                break;
            } else if (time[2] < Process.processMap.get(Process.process[2])) {
                time[2]++;
                getStatus(Process.process[2]);
                if (!status[0].equals("terminated")) left[0]++;
                if (!status[1].equals("terminated")) left[1]++;
                break;
            } else if (time[2] == Process.processMap.get(Process.process[2])) {
                getStatus(Process.process[2]);
            }
        }
    }

    void getStatus(char input) {
        for (int i = 0; i < 3; i++) status[i] = "waiting";
        Process process = new Process();
        for (int i = 0; i < 3; i++) {
            if (Process.process[i] == input) {
                status[i] = "running";
                break;
            }
            if (Processing.time[i] == process.getProcessTime(Process.process[i])) {
                status[i] = "terminated";
            }
        }
    }
}
