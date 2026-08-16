package social.network.ms_dialogs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import social.network.ms_dialogs.repository.constant.AbstractQueryDialog;
import social.network.ms_dialogs.repository.mapping.DialogMapper;
import social.network.ms_dialogs.repository.models.DialogEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Primary
public class DialogRepositoryImpl extends AbstractQueryDialog implements DialogRepository {
    private final JdbcTemplate template;
    private final DialogMapper mapper;

    @Autowired
    public DialogRepositoryImpl(JdbcTemplate template, DialogMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public DialogEntity save(DialogEntity entity) {
        getLogger().info("Init method save (return DialogEntity) in DialogRepository.");
        template.update(
                QUERY_SAVE,
                entity.getId(),
                entity.getPartner1(),
                entity.getPartner2(),
                entity.getCreatedDate());
        return this.getDialogById(entity.getId());
    }

    public DialogEntity getDialogByPartners(String partner1, String partner2) {
        getLogger().info("Init method getDialogByPartners (return DialogEntity) in DialogRepository.");
        return DataAccessUtils.singleResult(template.query(QUERY_GET_BY_PARTNERS, (ps) -> {
            ps.setString(1, partner1);
            ps.setString(2, partner2);
            ps.setString(3, partner1);
            ps.setString(4, partner2);
        }, mapper));
    }

    public DialogEntity getDialogById(String dialogId) {
        getLogger().info("Init method getById (return DialogEntity) in DialogRepository.");
        return DataAccessUtils.singleResult(
                template.query(QUERY_GET_BY_ID,
                        new ArgumentPreparedStatementSetter(new Object[]{dialogId}), mapper));
    }

    public DialogEntity getDialogByNames(String personId1, String personId2) {
        getLogger().info("Init method getDialogByNames (return DialogEntity) in DialogRepository.");
        DialogEntity saved = getDialogByPartners(personId1, personId2);
        return saved == null ? save(initDialog(personId1, personId2)) : saved;
    }

    public List<DialogEntity> getAllDialogsByUser(String personId) {
        return new ArrayList<>(template.query(QUERY_GET_BY_NAMES, mapper, personId, personId));
    }

    public boolean deleteById(String dialogId) {
        getLogger().info("Init method deleteById (return boolean) in DialogRepository.");
        return template.update(QUERY_DELETE_BY_ID, dialogId) > 0;
    }

    private String getCurrentDate() {
        return Date.from(Instant.now()).toString();
    }

    private DialogEntity initDialog(String personId1, String personId2) {
        String dialogId = String.valueOf(UUID.randomUUID());
        return new DialogEntity(dialogId, personId1, personId2, getCurrentDate(), Boolean.FALSE);
    }
}