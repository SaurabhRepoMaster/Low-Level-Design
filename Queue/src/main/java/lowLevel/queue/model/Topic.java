package lowLevel.queue.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {

    private final String topicName;
    private final String topicId;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;

    public Topic(@NonNull final String topicName,@NonNull final  String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public synchronized void addMessages(@NonNull final Message message)
    {
        messages.add(message);
    }

    public void addSubscribers(@NonNull final TopicSubscriber topicSubscriber)
    {
        subscribers.add(topicSubscriber);
    }


}
