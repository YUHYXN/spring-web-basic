package com.codeit.springwebbasic.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationDispatcher {

    // 컬렉션으로 빈 주입 받기
    private final List<NotificationService> allServices;

    @Autowired
    public NotificationDispatcher(List<NotificationService> allServices) {
        this.allServices = allServices;
        System.out.println("등록된 알림 서비스: " + allServices.size() + "개");
    }

    // 모든 알림 서비스에게 알림을 전송하겠다.
    public void broadcast(String recipient, String message) {
        allServices.forEach(service -> {
            service.sendNotification(recipient, message);
        });
    }

}
