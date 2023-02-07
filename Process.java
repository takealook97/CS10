import java.util.*;

public class Process {
    static final char[] process = new char[3];
    static final HashMap<Character, Integer> processMap = new HashMap<>();
    Queue<ProcessInfo> queue = new LinkedList<>();

    void initialize() {
        makeProcessMap();
        getRandom();
        queue.add(new ProcessInfo(process[0], getProcessTime(process[0])));
        queue.add(new ProcessInfo(process[1], getProcessTime(process[1])));
        queue.add(new ProcessInfo(process[2], getProcessTime(process[2])));
    }

    void makeProcessMap() {
        processMap.put('A', 3);
        processMap.put('B', 5);
        processMap.put('C', 7);
        processMap.put('D', 10);
        processMap.put('E', 15);
        processMap.put('F', 21);
    }

    int getProcessTime(char input) {
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
        process[0] = list.get(0);
        process[1] = list.get(1);
        process[2] = list.get(2);
    }


}
