package org.myuniv.system.course.services;

import org.myuniv.system.course.beans.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public interface NotificationsService {
    NotificationDto createNotification(NotificationDto notificationDto);

    List<NotificationDto> viewNotifications(String userId);

    NotificationDto viewNotification(Long notificationId);

}
