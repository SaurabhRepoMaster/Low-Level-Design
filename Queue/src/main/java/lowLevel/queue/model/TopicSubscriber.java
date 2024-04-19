package lowLevel.queue.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lowLevel.queue.publicInterface.ISubscriber;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class TopicSubscriber {

    private final AtomicInteger offset; //AtomicInteger is being used to handle multithreading concurrency.
    private final ISubscriber subscriber;

    public TopicSubscriber(@NonNull final ISubscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}
