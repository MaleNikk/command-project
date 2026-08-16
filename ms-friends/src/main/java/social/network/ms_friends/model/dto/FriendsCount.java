package social.network.ms_friends.model.dto;

public class FriendsCount {
    private int countFriends;

    public FriendsCount(int countFriends) {
        this.countFriends = countFriends;
    }

    public int getCountFriends() {
        return countFriends;
    }

    public void setCountFriends(int countFriends) {
        this.countFriends = countFriends;
    }
}