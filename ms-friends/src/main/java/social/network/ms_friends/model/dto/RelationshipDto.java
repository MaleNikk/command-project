package social.network.ms_friends.model.dto;

import java.util.UUID;

public class RelationshipDto {
    private UUID id;
    private FriendshipStatus friendshipStatus;
    private UUID friendId;
    private FriendshipStatus previousFriendshipStatus;
    private int rating;
    private boolean isDeleted;

    public RelationshipDto(UUID id, FriendshipStatus friendshipStatus, UUID friendId, FriendshipStatus previousFriendshipStatus,
                           int rating, boolean isDeleted) {
        this.id = id;
        this.friendshipStatus = friendshipStatus;
        this.friendId = friendId;
        this.previousFriendshipStatus = previousFriendshipStatus;
        this.rating = rating;
        this.isDeleted = isDeleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FriendshipStatus getStatusCode() {
        return friendshipStatus;
    }

    public void setStatusCode(FriendshipStatus friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

    public UUID getFriendId() {
        return friendId;
    }

    public void setFriendId(UUID friendId) {
        this.friendId = friendId;
    }

    public FriendshipStatus getPreviousStatusCode() {
        return previousFriendshipStatus;
    }

    public void setPreviousStatusCode(FriendshipStatus previousFriendshipStatus) {
        this.previousFriendshipStatus = previousFriendshipStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}