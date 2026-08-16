package social.network.ms_dialogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.ms_dialogs.dto.*;
import social.network.ms_dialogs.exception.management.ExceptionBuilder;
import social.network.ms_dialogs.logger.ApplicationLogging;
import social.network.ms_dialogs.repository.models.RequestMessage;
import social.network.ms_dialogs.repository.models.ResponseMessage;
import social.network.ms_dialogs.service.dialogs.DialogService;
import social.network.ms_dialogs.service.security.SecurityService;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@RequestMapping("/api/v1/dialogs")
@RestController
public class ApplicationController {

    private final ApplicationLogging logger;
    private final DialogService dialogService;
    private final SecurityService securityService;

    @Autowired
    public ApplicationController(ApplicationLogging logger, DialogService dialogService, SecurityService securityService) {
        this.logger = logger;
        this.dialogService = dialogService;
        this.securityService = securityService;
    }

    @PutMapping({"/{dialogId}"})
    public ResponseEntity<DialogDto> markReaded(@PathVariable String dialogId,
                                                @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Изменить статус сообщений на прочитанное.)");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.getDialog(id, dialogId)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public ResponseEntity<PageResponseDto<DialogDto>> getAllDialogsForTheUser(@RequestParam Integer page,
                                                                              @RequestParam String sort,
                                                                              @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Получить диалоги для пользователя.");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.getAllDialogs(id, page, sort)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<String> getDescriptionDialogs() {
        logger.printLog("DialogController class: Получить описание работы сервиса.");
        return ResponseEntity.ok("""
                (/api/v1/dialogs/unread) - Вызывается при входе на сайт отдаем авторизованному пользователю ко-во не прочитанных.
                                           Обновляется перезагрузкой страницы или щелканьями по разным разделам.
                (/api/v1/dialogs) - Вызывается при загрузке страницы диалогов, отдаем авторизованному пользователю список диалогов.
                                    Фронт формирует список диалогов. И под именем каждого пользователя отдаем последние сообщение.
                (/api/v1/dialogs/recipientId/{id}) - Вызывается при загрузке страницы диалогов, отдаем авторизованному пользователю список диалогов.
                                                     Так же создаем диалог если его нет, и создаем диалог для другого участника если у него нет.
                (/api/v1/dialogs/messages) - Вызывается при загрузке страницы диалогов.
                                             Отдаем авторизованному пользователю список диалогов и
                                             все сообщения с пагинацией с временной сортировкой,
                                             чтобы отображалась вся переписка с конкретным пользователем с начало новые сообщения.
                (/api/v1/dialogs/{dialogId}) - Вызывается при загрузке страницы диалогов.
                                               Изменяем статус сообщения на прочитанное и изменяем количество непрочитанных сообщений пользователя.
                                               При нажатие на разные диалоги. Создание диалогов и сообщений.
                                               Все сообщения работают через WebSocket в них мы берем сообщения с фронта и
                                               отправляем их в сервис где уже мы сохраняем их в бд каждое сообщение должно принадлежать одному диалогу.
                """
                );
    }

    @GetMapping({"/unread"})
    public ResponseEntity<UnreadCountDto> getUnreadMessages(@RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Получение общего количества непрочитанных сообщений.");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.getUnreadCountMessages(id)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping({"/unread"})
    public ResponseEntity<UnreadCountDto> unreadMessages(@RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Получение общего количества непрочитанных сообщений.");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.getUnreadCountMessages(id)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping({"/recipientId/{partnerId}"})
    public ResponseEntity<DialogDto> newDialogs(@PathVariable String partnerId,
                                                @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Инициализация диалога между пользователями.");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.getDialog(new RequestDialog(id, partnerId, initDialog()))) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping({"/messages"})
    public ResponseEntity<PageResponseDto<MessageDto>> getMessages(@RequestParam String recipientId,
                                                                   @RequestParam Integer page,
                                                                   @RequestParam String sort,
                                                                   @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Получение сообщений для конкретного диалога.");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.getAllMessages(id, recipientId, page, sort)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping({"/messages/{recipientId}"})
    public ResponseEntity<PageResponseDto<MessageDto>> setMessages(@RequestParam String recipientId,
                                                                   @RequestParam Integer page,
                                                                   @RequestParam String sort,
                                                                   @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Изменить статус сообщений для конкретного диалога.");
        String id = getIdFromJwt(headers);
        if (id != null) {
            dialogService.updateStatusMessages(id, recipientId, page, sort);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping({"/message/edit"})
    public ResponseEntity<MessageDto> editMessageByMessageId(@RequestBody RequestEditMessage message,
                                                             @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Редактирование сообщение пользователя.");
        String id = getIdFromJwt(headers);
        if (id != null) {
            if (!Objects.equals(id, message.authorId())) {
                throw ExceptionBuilder.initNFException("You not author message! Only author can edit messages!");
            } else {
                return ResponseEntity.ok(dialogService.editMessageByAuthor(message));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping({"/sending/message"})
    public ResponseEntity<ResponseMessage> editMessageByMessageId(@RequestBody RequestMessage message,
                                                                  @RequestHeader HttpHeaders headers) {
        logger.printLog("DialogController class: Отправка сообщений собеседнику.");
        String id = getIdFromJwt(headers);
        return id != null ? ResponseEntity.ok(dialogService.sendingMessage(message)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private String initDialog() {
        return String.format("Init dialog at time: %s", Date.from(Instant.now()));
    }

    private String getIdFromJwt(HttpHeaders headers) {
        return securityService.getUserId(headers);
    }
}