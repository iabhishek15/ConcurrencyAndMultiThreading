public class RaceCondition {

    int iteration = 10000;
    Counter counter = new Counter();

    class Counter {
        int counter = 0;

        //To protect the incrementCounter from raceCondition we will synchronized keyword.
        synchronized void incrementCounter() {
            this.counter++;
        }
    }

    Thread t1 = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < iteration; ++i) {
                counter.incrementCounter();
            }

            System.out.println("Counter value after completion iteration from first thread : " + counter.counter);
        }
    };

    Thread t2 = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < iteration; ++i) {
                counter.incrementCounter();
            }

            System.out.println("Counter value after completion iteration from second thread : " + counter.counter);
        }
    };


    public static void main(String args[]) throws InterruptedException {
        RaceCondition raceCondition = new RaceCondition();
        raceCondition.run();
    }

    void run() throws InterruptedException {
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Execution is completed with value of counter as : " + counter.counter);
    }

}
