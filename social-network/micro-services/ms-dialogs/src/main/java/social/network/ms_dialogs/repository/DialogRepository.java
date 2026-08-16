package social.network.ms_dialogs.repository;

import social.network.ms_dialogs.repository.models.DialogEntity;

import java.util.List;

public interface DialogRepository {
    DialogEntity save(DialogEntity entity);

    DialogEntity getDialogById(String dialogId);

    DialogEntity getDialogByNames(String personId1, String personId2);

    DialogEntity getDialogByPartners(String partner1, String partner2);

    boolean deleteById(String id);

    List<DialogEntity> getAllDialogsByUser(String personId);
}
