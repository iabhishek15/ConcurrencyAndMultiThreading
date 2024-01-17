public class DeadLockCondition {

    String a = "a";
    String b = "b";

    Thread thread1 = new Thread() {
        @Override
        public void run() {

            synchronized (a) {
                System.out.println("a is locked in first thread");
                try {
                    Thread.sleep(1000);
                    System.out.println("Sleep state finished in first thread !");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b) {
                    System.out.println("b is locked by first thread");
                }
            }

        }
    };

    Thread thread2 = new Thread() {
        @Override
        public void run() {

            synchronized (b) {
                System.out.println("b is locked in second thread");
                synchronized (a) {
                    System.out.println("a is locked by second thread");
                }
            }

        }
    };

    public static void main(String args[]) {
        DeadLockCondition deadLockCondition = new DeadLockCondition();
        deadLockCondition.run();
    }

    void run() {
        thread1.start();
        thread2.start();
    }


}
