public class Output {
    void printInit() {
        System.out.println("프로세스 시간 = A : 3초 | B : 5초 | C : 7초 | D : 10초 | E : 15초 | F : 21초");
        System.out.println("이 프로그램은 6개의 프로세스(A, B, C, D, E, F) 중 랜덤으로");
        System.out.println("프로세스A(3초), 프로세스B(5초),프로세스C(7초) 3개를 선택하고 라운드로빈 방식으로 실행합니다." + "\n");
    }

    void printProcess() {
        while (true) {
            for (int i = 0; i < 3; i++) {

            }
        }
    }

    void printReady() {
        Process process = new Process();
        for (int i = 0; i < 3; i++) {
            System.out.println(Process.process[i] + "(ready), 0 / "
                    + process.getProcessTime(Process.process[i]) + "sec");
        }
        System.out.println(".");
    }

    void printFrame(char[] input) {
        //(해시맵키)(상태), (진행) / (시간), waiting (대기시간) sec -> 3줄 후 "."
        Process process = new Process();
        while (true) {
            int[] time = process.getTime();
            for (int i = 0; i < 3; i++) {
                System.out.print(input[i] + "(" + new Status(Process.process[i]) + "), ");
                System.out.print(time[i] + " / " + process.getProcessTime(Process.process[i]) + "sec , ");
                System.out.println("waiting " + new Status(Process.process[i]).left);
            }
            System.out.println(".");
        }
    }

    void printEnd() {
        //평균 대기시간 = (대기시간 총합) / 3 = (결과)sec
        //평균 반환시간 = (완료시간+대기시간 총합) / 3 = (결과)sec
    }
}
