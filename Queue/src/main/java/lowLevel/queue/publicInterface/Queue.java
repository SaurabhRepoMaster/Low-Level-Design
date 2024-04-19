package lowLevel.queue.publicInterface;

import lombok.NonNull;
import lowLevel.queue.handler.TopicHandler;
import lowLevel.queue.model.Message;
import lowLevel.queue.model.Topic;
import lowLevel.queue.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {

    private final Map<String, TopicHandler> topicHandlers;

    public Queue()
    {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(final String topicName)
    {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(),topicHandler);
        System.out.println("Created topic: "+ topic.getTopicName());
        return topic;
    }

    public void subscribe(@NonNull final ISubscriber subscriber, @NonNull final Topic topic) {
        topic.addSubscribers(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(@NonNull final Topic topic, @NonNull final Message message) {
        topic.addMessages(message);
        System.out.println(message.getMsg() + " published to topic: " + topic.getTopicName());
        new Thread(()->topicHandlers.get(topic.getTopicId()).publish()).start();//we are doing it using thread...
        //it would not block other threads.
    }

    public void resetOffset(@NonNull final Topic topic, @NonNull final ISubscriber subscriber, @NonNull final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
            if(topicSubscriber.getSubscriber().equals(subscriber)){
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(()->topicHandlers.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
            }
        }
    }

}
