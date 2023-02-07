public class Output {
    void printInit() {
        System.out.println("프로세스 시간 = A : 3초 | B : 5초 | C : 7초 | D : 10초 | E : 15초 | F : 21초");
        System.out.println("이 프로그램은 6개의 프로세스(A, B, C, D, E, F) 중 랜덤으로");
        System.out.println("프로세스A(3초), 프로세스B(5초),프로세스C(7초) 3개를 선택하고 라운드로빈 방식으로 실행합니다.");
        System.out.println("-----------------------------------------------------------------------");
    }

    void printReady() {
        Process process = new Process();
        for (int i = 0; i < 3; i++) {
            System.out.println(Process.process[i] + "(ready), 0 / "
                    + process.getProcessTime(Process.process[i]) + "sec");
        }
    }

    void printProcess() {
        //(해시맵키)(상태), (진행) / (시간), waiting (대기시간) sec -> 3줄 후 "."
        Process process = new Process();
        Processing processing = new Processing();
        while (Processing.time[2] != process.getProcessTime(Process.process[2])) {
            System.out.println(".");
            processing.processing();
            for (int i = 0; i < 3; i++) {
                System.out.print(Process.process[i] + "(" + Processing.status[i] + "), ");
                System.out.print(Processing.time[i] + " / " + process.getProcessTime(Process.process[i]) + "sec , ");
                System.out.println("waiting " + Processing.waiting[i] + "sec");
            }
        }
    }

    void printEnd() {
        //평균 대기시간 = (대기시간 총합) / 3 = (결과)sec
        //평균 반환시간 = (완료시간+대기시간 총합) / 3 = (결과)sec
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("라운드로빈 방식 스케줄링이 종료되었습니다.");
        System.out.print("평균 대기시간 = (" + Processing.waiting[0] + " + " + Processing.waiting[1] + " + " + Processing.waiting[2] + ") / 3 = ");
        System.out.println(String.format("%.1f", (double) (Processing.waiting[0] + Processing.waiting[1] + Processing.waiting[2]) / 3) + "sec");
        int[] sum = new int[3];
        for (int i = 0; i < 3; i++) {
            sum[i] = Processing.time[i] + Processing.waiting[i];
        }
        System.out.print("평균 반환시간 = (" + sum[0] + " + " + sum[1] + " + " + sum[2] + ") / 3 = ");
        System.out.println(String.format("%.1f", (double) (sum[0] + sum[1] + sum[2]) / 3) + "sec");

    }
}
