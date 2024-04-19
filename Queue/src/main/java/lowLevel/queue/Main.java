package lowLevel.queue;

import lowLevel.queue.model.Message;
import lowLevel.queue.model.Topic;
import lowLevel.queue.publicInterface.Queue;
import lowLevel.queue.subscribers.SleepingSubscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        final Queue queue = new Queue();
        final Topic topic1 = queue.createTopic("T1");
        final Topic topic2 = queue.createTopic("T2");
        final SleepingSubscriber sub1 = new SleepingSubscriber("Subcriber1", 10000);
        final SleepingSubscriber sub2 = new SleepingSubscriber("Subcriber2", 10000);
        queue.subscribe(sub1, topic1);
        queue.subscribe(sub2, topic1);

        final SleepingSubscriber sub3 = new SleepingSubscriber("Subcriber3", 5000);
        queue.subscribe(sub3, topic2);

        queue.publish(topic1, new Message("Message1"));
        queue.publish(topic1, new Message("Message2"));

        queue.publish(topic2, new Message("Message3"));

        Thread.sleep(15000);

        queue.publish(topic2, new Message("Message4"));
        queue.publish(topic1, new Message("Message5"));

        queue.resetOffset(topic1,sub1,0);
    }
}