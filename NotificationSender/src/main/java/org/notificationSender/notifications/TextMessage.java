package org.notificationSender.notifications;

import org.notificationSender.notificationSender.NotificationSender;

public class TextMessage extends Notification{

    public TextMessage(NotificationSender notificationSender) {
        super(notificationSender);
    }

    @Override
    public void sendMessage() {
        notificationSender.sendNotification();
    }
}
