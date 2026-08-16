package social.network.ms_friends.storage;

import social.network.ms_friends.model.dto.FriendshipStatus;
import social.network.ms_friends.model.entity.RelationshipEntity;

import java.util.List;
import java.util.UUID;

public interface ApplicationRepository {

    RelationshipEntity save(RelationshipEntity entity);

    RelationshipEntity edit(RelationshipEntity entity);

    RelationshipEntity block(RelationshipEntity entity);

    RelationshipEntity get(UUID id, UUID friendId);

    List<RelationshipEntity> getFriends(UUID id, FriendshipStatus status);

    List<RelationshipEntity> getRecommend(UUID id, FriendshipStatus status);

    List<RelationshipEntity> getAll();
}