package org.myuniv.system.course.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dto.NotificationDto;
import org.myuniv.system.course.beans.requests.NotificationRequest;
import org.myuniv.system.course.beans.responses.NotificationResponse;
import org.myuniv.system.course.services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController @RequestMapping("/notifications") public class NotificationsController {

    @Autowired NotificationsService notificationsService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NotificationResponse> createNotification(
        @Validated @RequestBody NotificationRequest notificationDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        NotificationDto notificationDto = modelMapper.map(notificationDetails, NotificationDto.class);

        NotificationDto createdNotification = notificationsService.createNotification(notificationDto);

        NotificationResponse returnValue = modelMapper.map(createdNotification, NotificationResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<NotificationResponse>> viewNotifications(@RequestParam String userId) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<NotificationDto> notifications = notificationsService.viewNotifications(userId);

        List<NotificationResponse> returnValueList =
            notifications.stream().map(notification -> modelMapper.map(notification, NotificationResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(returnValueList);
    }

    @GetMapping(value = "/{notificationId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NotificationResponse> viewNotification(@PathVariable Long notificationId) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        NotificationDto notification = notificationsService.viewNotification(notificationId);

        NotificationResponse returnValue = modelMapper.map(notification, NotificationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
