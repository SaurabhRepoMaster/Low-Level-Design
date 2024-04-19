package lowLevel.queue.handler;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lowLevel.queue.model.Topic;
import lowLevel.queue.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {

    private final Topic topic;
    private final Map<String,SubscribeWorker> subscribeWorkers;

    public TopicHandler(@NonNull final Topic topic)
    {
        this.topic = topic;
        subscribeWorkers = new HashMap<>();
    }

    public void publish(){
        for(TopicSubscriber topicSubscriber: topic.getSubscribers())
        {
            startSubscriberWorker(topicSubscriber);
        }
    }

    public void startSubscriberWorker(TopicSubscriber topicSubscriber) {
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        if(!subscribeWorkers.containsKey(subscriberId))
        {
            final SubscribeWorker subscribeWorker = new SubscribeWorker(topic, topicSubscriber);
            subscribeWorkers.put(subscriberId,subscribeWorker);
            new Thread(subscribeWorker).start();
        }
        final SubscribeWorker subscriberWorker = subscribeWorkers.get(subscriberId);
        subscriberWorker.wakeupIfNeeded();
    }


}
