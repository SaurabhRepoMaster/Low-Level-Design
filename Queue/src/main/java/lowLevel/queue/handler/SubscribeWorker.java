package lowLevel.queue.handler;

import lombok.NonNull;
import lombok.SneakyThrows;
import lowLevel.queue.model.Message;
import lowLevel.queue.model.Topic;
import lowLevel.queue.model.TopicSubscriber;

public class SubscribeWorker implements Runnable{

    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscribeWorker(@NonNull final Topic topic,@NonNull final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

   // @SneakyThrows//In Java, the sneaky throw concept allows us to throw any checked exception without defining it explicitly in the method signature.
    @Override
    public void run() {
        synchronized (topicSubscriber)
        {
            do{
                int currentOffSet = topicSubscriber.getOffset().get();
                while(currentOffSet>=topic.getMessages().size())
                {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Message message = topic.getMessages().get(currentOffSet);
                try {
                    topicSubscriber.getSubscriber().consume(message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                //The Java.util.concurrent.atomic.AtomicLong.compareAndSet() is an inbuilt method in java that
                // sets the value to the passed value in the parameter if the current value is equal to the expected
                // value which is also passed in the parameter. The function returns a boolean value which gives us
                // an idea if the update was done or not.
                topicSubscriber.getOffset().compareAndSet(currentOffSet,currentOffSet+1);
            }while(true);
        }
    }

    public void wakeupIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
            //We can also use notifyAll() here.
        }
    }
}
