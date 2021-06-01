package org.myuniv.system.course.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dao.NotificationEntity;
import org.myuniv.system.course.beans.dto.NotificationDto;
import org.myuniv.system.course.repositories.NotificationsRepository;
import org.myuniv.system.course.services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service public class NotificationsServiceImpl implements NotificationsService {

    NotificationsRepository notificationsRepository;

    @Autowired public NotificationsServiceImpl(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    @Override public NotificationDto createNotification(NotificationDto notificationDetails) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        NotificationEntity notificationEntity = mapper.map(notificationDetails, NotificationEntity.class);

        notificationsRepository.save(notificationEntity);

        NotificationDto returnValue = mapper.map(notificationEntity, NotificationDto.class);

        return returnValue;
    }

    @Override public List<NotificationDto> viewNotifications(String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // assume user Id is of a student- can be checked in database and hence applying receiver group for students
        List<NotificationEntity> notifications = notificationsRepository.findAllByReceiverGroup("AllStudents");

        List<NotificationDto> returnValue =
            notifications.stream().map(notification -> mapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());

        return returnValue;
    }

    @Override public NotificationDto viewNotification(Long notificationId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Optional<NotificationEntity> byId = notificationsRepository.findById(notificationId);

        NotificationDto returnValue = new NotificationDto();

        if (byId.isPresent()) {
            returnValue = mapper.map(byId.get(), NotificationDto.class);
        }

        return returnValue;
    }

}
