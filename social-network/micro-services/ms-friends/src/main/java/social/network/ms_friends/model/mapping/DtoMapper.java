package social.network.ms_friends.model.mapping;

import social.network.ms_friends.model.dto.FriendshipStatus;
import social.network.ms_friends.model.dto.RelationshipDto;
import social.network.ms_friends.model.entity.RelationshipEntity;

import java.util.UUID;
import java.util.stream.Stream;

public final class DtoMapper {

    public static RelationshipDto from(RelationshipEntity entity) {
        return null;
    }

    public static RelationshipEntity from(RelationshipDto dto) {
        return null;
    }

    public static RelationshipEntity getInstance(UUID friend1, UUID friend2){
        long id = 0L;
        for (byte i : friend1.toString().concat(friend2.toString()).getBytes()) {
            id = id + i;
        }
        return new RelationshipEntity(
                id,
                friend1,
                FriendshipStatus.REQUEST_TO,
                friend2,
                FriendshipStatus.REQUEST_TO,
                null,
                0,
                System.currentTimeMillis(),
                System.currentTimeMillis());
    }
}