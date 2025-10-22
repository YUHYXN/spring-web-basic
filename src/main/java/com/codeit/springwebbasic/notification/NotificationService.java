package com.codeit.springwebbasic.notification;

public interface NotificationService {
    void sendNotification(String recipient, String message);    // recipient 받는 사람, message 보낼 메시지
}
