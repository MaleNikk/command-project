package social.network.ms_friends.model.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import social.network.ms_friends.model.dto.FriendshipStatus;
import social.network.ms_friends.model.entity.RelationshipEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class RelationshipMapper implements RowMapper<RelationshipEntity> {

    @Override
    public RelationshipEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RelationshipEntity(
                rs.getLong("id"),
                UUID.fromString(rs.getString("friend_1")),
                FriendshipStatus.valueOf(rs.getString("current_status")),
                UUID.fromString(rs.getString("friend_2")),
                FriendshipStatus.valueOf(rs.getString("previous_status")),
                UUID.fromString(rs.getString("block_author")),
                rs.getInt("rating"),
                rs.getLong("time_register"),
                rs.getLong("time_update")
                );
    }
}