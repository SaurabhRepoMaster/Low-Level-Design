package lowLevel.queue.subscribers;

import lowLevel.queue.model.Message;
import lowLevel.queue.publicInterface.ISubscriber;
import lowLevel.queue.publicInterface.Queue;

public class SleepingSubscriber implements ISubscriber {

    private final String id;
    private final int sleepTimeInMillis;

    public SleepingSubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber: "+id+" started consuming "+message.getMsg());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber: " + id + " done consuming: " + message.getMsg());
    }

}
