package social.network.ms_friends.model.entity;

import social.network.ms_friends.model.dto.FriendshipStatus;

import java.util.UUID;

public class RelationshipEntity {
    private long id;
    private UUID friend1;
    private FriendshipStatus currentStatus;
    private UUID friend2;
    private FriendshipStatus previousStatus;
    private UUID blockAuthor;
    private int rating;
    private long timeRegister;
    private long timeUpdate;

    public RelationshipEntity() {
    }

    public RelationshipEntity(long id, UUID friend1, FriendshipStatus currentStatus,
                              UUID friend2, FriendshipStatus previousStatus, UUID blockAuthor, int rating,
                              long timeRegister, long timeUpdate) {
        this.id = id;
        this.friend1 = friend1;
        this.currentStatus = currentStatus;
        this.friend2 = friend2;
        this.previousStatus = previousStatus;
        this.blockAuthor = blockAuthor;
        this.rating = rating;
        this.timeRegister = timeRegister;
        this.timeUpdate = timeUpdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getFriend1() {
        return friend1;
    }

    public void setFriend1(UUID friend1) {
        this.friend1 = friend1;
    }

    public FriendshipStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(FriendshipStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public UUID getFriend2() {
        return friend2;
    }

    public void setFriend2(UUID friend2) {
        this.friend2 = friend2;
    }

    public FriendshipStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(FriendshipStatus previousStatus) {
        this.previousStatus = previousStatus;
    }

    public UUID getBlockAuthor() {
        return blockAuthor;
    }

    public void setBlockAuthor(UUID blockAuthor) {
        this.blockAuthor = blockAuthor;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getTimeRegister() {
        return timeRegister;
    }

    public void setTimeRegister(long timeRegister) {
        this.timeRegister = timeRegister;
    }

    public long getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(long timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}