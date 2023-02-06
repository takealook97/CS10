import java.util.*;

public class Process {
    char[] process;
    Queue<ProcessInfo> queue = new LinkedList<>();

    void initialize() {
        getRandom();
        queue.add(new ProcessInfo(process[0], makeProcess(process[0])));
        queue.add(new ProcessInfo(process[1], makeProcess(process[1])));
        queue.add(new ProcessInfo(process[2], makeProcess(process[2])));
    }

    int makeProcess(char input) {
        HashMap<Character, Integer> processMap = new HashMap<>();
        processMap.put('A', 3);
        processMap.put('B', 5);
        processMap.put('C', 7);
        processMap.put('D', 10);
        processMap.put('E', 15);
        processMap.put('F', 21);
        return processMap.get(input);
    }

    void getRandom() {
        Set<Character> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < 3) {
            set.add((char) (random.nextInt(6) + 65));
        }
        List<Character> list = new ArrayList<>(set);
        Collections.sort(list);

        process = new char[3];
        Arrays.sort(process);
        process[0] = list.get(0);
        process[1] = list.get(1);
        process[2] = list.get(2);
    }

}
