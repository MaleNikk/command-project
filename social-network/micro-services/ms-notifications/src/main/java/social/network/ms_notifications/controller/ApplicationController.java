package social.network.ms_notifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.ms_notifications.logging.ApplicationLogging;
import social.network.ms_notifications.model.dto.*;
import social.network.ms_notifications.security.ApplicationSecurity;
import social.network.ms_notifications.service.ApplicationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class ApplicationController {

    private final ApplicationService applicationService;

    private final ApplicationLogging logging;

    private final ApplicationSecurity security;

    @Autowired
    public ApplicationController(ApplicationService applicationService,
                                 ApplicationLogging logging,
                                 ApplicationSecurity security) {
        this.applicationService = applicationService;
        this.logging = logging;
        this.security = security;
    }

    // method send notification to kafka for any services
    @PutMapping("/send")
    public ResponseEntity<Boolean> pushNotificationToKafka(@RequestHeader HttpHeaders headers,
                                                           @RequestBody NotificationDto notification) {
        logging.printDebug("Application controller: call method push notification to kafka.");
        return security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION)) != null ?
                ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.sendNotificationToKafka(notification)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Boolean.FALSE);
    }

    // get current settings for account
    @GetMapping("/settings")
    public ResponseEntity<NotificationSettingsDto> getCurrentSettingsForAccount(@RequestHeader HttpHeaders headers) {
        logging.printDebug("Application controller: call method get current account's settings.");
        UUID id = security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        if (id != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(applicationService.getCurrentSettings(id));
        } else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // change current settings for notification
    @PutMapping("/settings")
    public ResponseEntity<NotificationSettingsDto> updateCurrentSettingsForAccount(
            @RequestBody NotificationUpdateDto update, @RequestHeader HttpHeaders headers) {
        logging.printDebug("Application controller: call method update current account's settings.");
        UUID id = security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return id == null ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.updateCurrentSettings(update));
    }

    // create settings for account
    @PostMapping("/settings")
    public ResponseEntity<Boolean> createSettingForAccount(@RequestHeader HttpHeaders headers) {
        logging.printDebug("Application controller: call method create settings for account.");
        UUID id = security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return id == null ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.createSettingsForNotifications(id));
    }

//    @PostMapping("/{id}")
//    public ResponseEntity<String> createSettings(@RequestHeader HttpHeaders headers, @PathVariable String id){
//        logging.printInfo("Call method create with id: " + id);
//        return "true";
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<NotificationSettingsDto> findById(@RequestHeader HttpHeaders headers, @PathVariable String id){
//        logging.printInfo("Call method find by id notification settings.");
//        return new NotificationSettingsDto();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> update(@RequestHeader HttpHeaders headers, @PathVariable String id,
//                                       @RequestBody UpsertNotificationSettingRequest request){
//        logging.printInfo("Call method update Notification by id.");
//        return ResponseEntity.status(200).build();
//    }

    // change property on/off notification
    @PutMapping("/readed")
    public ResponseEntity<NotificationStatus> updateReedNotification(@RequestHeader HttpHeaders headers){
        logging.printDebug("Application controller: call method update property notifications for account.");
        UUID id = security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return id == null ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.onOffNotification(id));
    }

    @PutMapping("/readed/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody UpsertNotificationSettingRequest request){
        logging.printInfo("Call method update notification.");
        return ResponseEntity.status(200).build();
    }

    // get page of notifications for account
    @GetMapping("/page")
    public ResponseEntity<PageModelNotificationsDto> getPageNotificationsForAccount(
            @RequestParam String in,
            @RequestParam Boolean required,
            @RequestParam Integer schema,
            @RequestHeader HttpHeaders headers ){
        logging.printDebug("Application controller: call method get pages notifications for account.");
        UUID id = security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return id == null ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.getPagesNotifications(id));
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<List<NotificationDto>> findByReceiverId(@PathVariable Integer page,
                                                                  @RequestBody UpsertNotificationRequest request){
        logging.printInfo("Call method find Notification by id.");
        return ResponseEntity.ok(null);
    }

    // get count notifications for account
    @GetMapping("/count")
    public ResponseEntity<NotificationCountDto> getCountNotifications(@RequestHeader HttpHeaders headers){
        logging.printDebug("Application controller: call method get count notifications for account.");
        UUID id = security.checkToken(headers.getFirst(HttpHeaders.AUTHORIZATION));
        return id == null ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.getCountNotification(id));
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<NotificationCount> getCountFromReceiverId(@PathVariable String id){
        logging.printInfo("Call method get count Notification from receiverId.");
        return ResponseEntity.ok(null);
    }
}