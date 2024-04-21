package org.threadExecutor;

public class Tester {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Thread: "+Thread.currentThread().getName()+" is executing this task "+taskNumber);
                //System.out.println("Executing task " + taskNumber);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Thread: "+Thread.currentThread().getName()+" has completed executing this task "+taskNumber);
            });
        }

        // Wait for all tasks to complete
        executor.waitForCompletion();

        // Shutdown the executor
        executor.shutdown();
    }

}
