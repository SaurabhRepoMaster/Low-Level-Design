package org.notificationSender.notifications;

import org.notificationSender.notificationSender.NotificationSender;

public class QRMessage extends Notification{

    public QRMessage(NotificationSender notificationSender) {
        super(notificationSender);
    }

    @Override
    public void sendMessage() {
        //call some method to build QRCode
        notificationSender.sendNotification();
    }
}
