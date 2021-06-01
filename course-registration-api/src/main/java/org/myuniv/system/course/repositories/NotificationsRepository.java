package org.myuniv.system.course.repositories;

import org.myuniv.system.course.beans.dao.NotificationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationsRepository extends CrudRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByReceiverGroup(String receiverGroup);
}
