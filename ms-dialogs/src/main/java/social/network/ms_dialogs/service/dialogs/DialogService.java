package social.network.ms_dialogs.service.dialogs;

import social.network.ms_dialogs.dto.*;
import social.network.ms_dialogs.repository.models.RequestMessage;
import social.network.ms_dialogs.repository.models.ResponseMessage;

public interface DialogService {
    DialogDto getDialog(String partnerId, String dialogId);

    DialogDto getDialog(RequestDialog request);

    PageResponseDto<DialogDto> getAllDialogs(String personId, int page, String sort);

    PageResponseDto<MessageDto> getAllMessages(String partnerId1, String partnerId2, int page, String sort);

    MessageDto editMessageByAuthor(RequestEditMessage message);

    MessageDto pushMessage(RequestMessage message);

    MessageDto getMessageById(String messageId);

    UnreadCountDto getUnreadCountMessages(String personId);

    ResponseMessage sendingMessage(RequestMessage message);

    void updateStatusMessages(String dialogId, String personId, int page, String sort);
}
