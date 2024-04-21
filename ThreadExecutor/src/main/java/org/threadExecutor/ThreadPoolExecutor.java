package org.threadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolExecutor {
    private final BlockingQueue<Runnable> taskQueue;
    // BlockingQueue provides a way for threads to block when attempting
    // to take an element from an empty queue (take() method) or when attempting
    // to insert an element into a full queue (put() method).
    // This blocking behavior is essential for implementing a thread pool,
    // as it allows worker threads to wait efficiently for tasks when the queue is empty.
    private final WorkerThread[] workerThreads;
    private int completedTasks = 0;


    public ThreadPoolExecutor(int numThreads) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workerThreads = new WorkerThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            workerThreads[i] = new WorkerThread();
            workerThreads[i].start();
        }
    }

    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

        private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                    taskCompleted();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    private synchronized void taskCompleted() {
        completedTasks++;
        if (completedTasks == 10) {
            notifyAll();
        }
    }

    public synchronized void waitForCompletion() {
        while (completedTasks < 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void shutdown() {
        for (WorkerThread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }
}
