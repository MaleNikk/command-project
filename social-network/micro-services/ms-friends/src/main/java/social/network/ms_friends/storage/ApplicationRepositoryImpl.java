package social.network.ms_friends.storage;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import social.network.ms_friends.logging.ApplicationLogger;
import social.network.ms_friends.model.dto.FriendshipStatus;
import social.network.ms_friends.model.entity.RelationshipEntity;
import social.network.ms_friends.model.mapping.RelationshipMapper;

import java.util.List;
import java.util.UUID;

@Repository
public class ApplicationRepositoryImpl extends StorageQueries implements ApplicationRepository {

    private final ApplicationLogger logger;

    private final JdbcTemplate template;

    private final RelationshipMapper mapper;

    @Autowired
    public ApplicationRepositoryImpl(ApplicationLogger logger, JdbcTemplate template, RelationshipMapper mapper) {
        this.logger = logger;
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public RelationshipEntity save(RelationshipEntity entity) {
        logger.printLog("Application storage: call method save Relationship.", Level.INFO);
        return DataAccessUtils.singleResult(template.query(QUERY_SAVE, (ps) -> {
            ps.setLong(1, entity.getId());
            ps.setString(2,String.valueOf(entity.getFriend1()));
            ps.setString(3, String.valueOf(entity.getCurrentStatus()));
            ps.setString(4, String.valueOf(entity.getFriend2()));
            ps.setString(5, String.valueOf(entity.getPreviousStatus()));
            ps.setString(6, String.valueOf(entity.getFriend2()));
            ps.setInt(7, entity.getRating());
            ps.setLong(8, entity.getTimeRegister());
            ps.setLong(9, entity.getTimeUpdate());
        }, mapper));
    }

    @Override
    public RelationshipEntity edit(RelationshipEntity entity) {
        logger.printLog("Application storage: call method edit Relationship.", Level.INFO);
        template.update(QUERY_EDIT_STATUS, (ps -> {
            ps.setString(1,entity.getCurrentStatus().name());
            ps.setString(2,entity.getPreviousStatus().name());
            ps.setLong(3, System.currentTimeMillis());
            ps.setLong(4,entity.getId());
        }));
        return get(entity.getFriend1(), entity.getFriend2());
    }

    @Override
    public RelationshipEntity block(RelationshipEntity entity) {
        logger.printLog("Application storage: call method block Relationship.", Level.INFO);
        return null;
    }

    @Override
    public RelationshipEntity get(UUID friend1, UUID friend2) {
        logger.printLog("Application storage: call method get Relationship by id friends.", Level.INFO);
        return DataAccessUtils.singleResult(template.query(QUERY_GET_BY_FRIENDS, (ps) ->
        {
            ps.setString(1,String.valueOf(friend1));
            ps.setString(2,String.valueOf(friend2));
            ps.setString(3,String.valueOf(friend1));
            ps.setString(4,String.valueOf(friend2));
        }, mapper));
    }

    @Override
    public List<RelationshipEntity> getFriends(UUID id, FriendshipStatus status) {
        logger.printLog("Application storage: call method get Relationships.", Level.INFO);
        return List.of();
    }

    @Override
    public List<RelationshipEntity> getRecommend(UUID id, FriendshipStatus status) {
        logger.printLog("Application storage: call method get recommend Relationships.", Level.INFO);
        return List.of();
    }

    @Override
    public List<RelationshipEntity> getAll() {
        logger.printLog("Application storage: call method get all Relationships.", Level.INFO);
        return List.of();
    }
}