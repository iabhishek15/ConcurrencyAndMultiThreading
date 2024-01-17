import java.util.ArrayList;
import java.util.List;

public class RunnableThread implements Runnable{

    private final Integer limit;

    public RunnableThread(Integer limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        int a = 0;
        for (int i = 0; i < limit; i++) {
            a += 1;
        }
        System.out.println(a);
    }


    public static void main(String args[]) {

        List<Thread> threads = new ArrayList<>();
        int threadRunningCount = 0;

        for (int i = 0; i < 100; ++i) {
            RunnableThread myRunnableThread = new RunnableThread(i * 10);

            Thread worker = new Thread(myRunnableThread);

            worker.setName(String.valueOf(i));
            worker.start();

            threads.add(worker);

            if (worker.isAlive()) {
                threadRunningCount++;
            }
        }

        System.out.println(threadRunningCount);

        int running = 0;

        do {
            running = 0;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    running++;
                }
            }

            System.out.println("Total running thread is : " + running);

        } while (running > 0);

    }

}
