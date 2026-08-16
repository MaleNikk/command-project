package social.network.ms_dialogs.dto.mapping;

import social.network.ms_dialogs.dto.*;
import social.network.ms_dialogs.repository.models.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public final class DtoMapper {
    public static MessageDto fromEntity(MessageEntity message, DialogEntity dialog) {
        return new MessageDto(
                message.getId(),
                message.getCreatedDate(),
                dialog.getPartner1(),
                dialog.getPartner2(),
                message.getMessage(),
                message.getStatus(),
                dialog.getId(),
                message.isDeleted());
    }

    public static DialogDto fromEntity(DialogEntity dialog, List<MessageEntity> messages) {
        List<MessageDto> dtoMessages = messages.stream().map((message) -> fromEntity(message, dialog)).toList();
        return new DialogDto(
                dialog.getId(),
                dtoMessages.stream().filter((message) -> message.readStatus().equals(ReadStatus.SENT)).toList().size(),
                dialog.getPartner1(),
                dialog.getPartner2(),
                dtoMessages,
                Boolean.FALSE);
    }

    public static MessageEntity fromRequest(RequestMessage request, String dialogId) {
        return new MessageEntity(
                request.data().id(),
                dialogId,
                request.recipientId().equals(request.data().conversationPartner1()) ? request.data().conversationPartner2() : request.recipientId(),
                request.data().messageText(),
                ReadStatus.SENT,
                request.data().time(),
                request.data().time(),
                Boolean.FALSE);
    }

    public static UnreadCountDto getInstanceUnreadCount(long unreadCountMessages) {
        return new UnreadCountDto(unreadCountMessages);
    }

    public static ResponseMessage from(RequestMessage request, String dialogId) {
        return new ResponseMessage(
                "MESSAGE",
                request.data().conversationPartner2(),
                new ResponseData(
                        Date.from(Instant.now()),
                        request.data().conversationPartner1(),
                        request.data().conversationPartner2(),
                        request.data().messageText(),
                        ReadStatus.SENT, dialogId,
                        request.data().id()));
    }

    private static SortObject getSort() {
        return new SortObject(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
    }
}
