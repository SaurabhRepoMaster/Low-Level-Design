package lowLevel.queue.publicInterface;

import lowLevel.queue.model.Message;

public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
