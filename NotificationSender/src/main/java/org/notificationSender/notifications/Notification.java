package org.notificationSender.notifications;

import org.notificationSender.notificationSender.NotificationSender;

public abstract class Notification {
    NotificationSender notificationSender;

    public Notification(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    abstract void sendMessage();
}
