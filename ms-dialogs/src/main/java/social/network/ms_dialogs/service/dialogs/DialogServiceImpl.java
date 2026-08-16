package social.network.ms_dialogs.service.dialogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.ms_dialogs.dto.*;
import social.network.ms_dialogs.dto.mapping.DtoMapper;
import social.network.ms_dialogs.exception.management.ExceptionBuilder;
import social.network.ms_dialogs.repository.DialogRepository;
import social.network.ms_dialogs.repository.MessageRepository;
import social.network.ms_dialogs.repository.models.DialogEntity;
import social.network.ms_dialogs.repository.models.MessageEntity;
import social.network.ms_dialogs.repository.models.RequestMessage;
import social.network.ms_dialogs.repository.models.ResponseMessage;
import social.network.ms_dialogs.service.kafka.KafkaService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DialogServiceImpl implements DialogService {
    private final DialogRepository dialogRepository;
    private final MessageRepository messageRepository;
    private final KafkaService kafkaService;
    private final PageableBuilder<MessageDto> responseMessages;
    private final PageableBuilder<DialogDto> responseDialogs;

    @Autowired
    public DialogServiceImpl(DialogRepository dialogRepository, MessageRepository messageRepository, KafkaService kafkaService) {
        this.dialogRepository = dialogRepository;
        this.messageRepository = messageRepository;
        this.kafkaService = kafkaService;
        this.responseMessages = new PageableBuilder<>();
        this.responseDialogs = new PageableBuilder<>();
    }

    @Override
    public DialogDto getDialog(String partnerId, String dialogId) {
        DialogEntity entity = dialogRepository.getDialogById(dialogId);
        List<MessageEntity> entities = new ArrayList<>(messageRepository.getMessagesByDialog(dialogId));
        updateStatusMessages(entity.getPartner1(), entity.getPartner2(), 0, (String)null);
        Collections.reverse(entities);
        return DtoMapper.fromEntity(entity, entities);
    }

    @Override
    public DialogDto getDialog(RequestDialog request) {
        String dialogId = dialogRepository.getDialogByNames(request.partnerId1(), request.partnerId2()).getId();
        if (request.message() != null) {
            messageRepository.save(request.message(), request.partnerId1(), dialogId);
        }

        return DtoMapper.fromEntity(dialogRepository.getDialogById(dialogId), messageRepository.getMessagesByDialog(dialogId));
    }

    @Override
    public PageResponseDto<DialogDto> getAllDialogs(String personId, int page, String sort) {
        List<DialogDto> dialogs = dialogRepository.getAllDialogsByUser(personId).stream().map((entity) -> {
            List<MessageEntity> messages = new ArrayList<>(messageRepository.getMessagesByDialog(entity.getId()));
            Collections.reverse(messages);
            return DtoMapper.fromEntity(entity, messages);
        }).toList();
        return responseDialogs.getPageable(new ArrayList<>(dialogs), page, sort);
    }

    @Override
    public PageResponseDto<MessageDto> getAllMessages(String partnerId1, String partnerId2, int page, String sort) {
        DialogEntity dialog = dialogRepository.getDialogByNames(partnerId1, partnerId2);
        List<MessageDto> messages = new ArrayList<>(messageRepository.getMessagesByDialog(dialog.getId())
                .stream()
                .map((entity) -> DtoMapper.fromEntity(entity, dialog))
                .toList());
        Collections.reverse(messages);
        return responseMessages.getPageable(messages, page, sort);
    }

    @Override
    public MessageDto editMessageByAuthor(RequestEditMessage message) {
        MessageEntity entity = messageRepository.getMessageById(message.messageId());
        DialogEntity dialog = dialogRepository.getDialogById(entity.getDialogId());
        return DtoMapper.fromEntity(messageRepository.update(message.messageId(), message.cause(), message.authorId()), dialog);
    }

    @Override
    public MessageDto pushMessage(RequestMessage message) {
        DialogEntity dialog = dialogRepository.getDialogByNames(message.data().conversationPartner1(), message.data().conversationPartner2());
        MessageDto dto = DtoMapper.fromEntity(messageRepository.save(message.data().messageText(), message.recipientId(), dialog.getId()), dialog);
        if (kafkaService.pushNotification(dto)) {
            throw ExceptionBuilder.initSNException("Notification not sending! Check connection with Notification service!");
        } else {
            return dto;
        }
    }

    @Override
    public MessageDto getMessageById(String messageId) {
        MessageEntity entity = messageRepository.getMessageById(messageId);
        DialogEntity dialog = dialogRepository.getDialogById(entity.getDialogId());
        return DtoMapper.fromEntity(entity, dialog);
    }

    @Override
    public UnreadCountDto getUnreadCountMessages(String personId) {
        return DtoMapper.getInstanceUnreadCount(messageRepository.getCountReceivedUnreadMessages(personId));
    }

    @Override
    public ResponseMessage sendingMessage(RequestMessage message) {
        String dialogId = dialogRepository.getDialogByPartners(message.data().conversationPartner1(), message.data().conversationPartner2()).getId();
        messageRepository.save(message.data().messageText(), message.recipientId(), dialogId);
        return DtoMapper.from(message, dialogId);
    }

    @Override
    public void updateStatusMessages(String authorId, String personId, int page, String sort) {
        String dialogId = dialogRepository.getDialogByNames(authorId, personId).getId();
        messageRepository.batchUpdate(dialogId, personId);
    }
}