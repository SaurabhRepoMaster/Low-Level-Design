package org.notificationSender;

import org.notificationSender.notificationSender.Email;
import org.notificationSender.notificationSender.SMS;
import org.notificationSender.notifications.QRMessage;
import org.notificationSender.notifications.TextMessage;

public class BridgeMain {

    public static void main(String[] args) {
        QRMessage qrMessage = new QRMessage(new Email());
        qrMessage.sendMessage();

        QRMessage qrMessage1 = new QRMessage(new SMS());
        qrMessage1.sendMessage();

        TextMessage textMessage = new TextMessage(new Email());
        textMessage.sendMessage();

        TextMessage textMessage1 = new TextMessage(new SMS());
        textMessage1.sendMessage();
    }
}