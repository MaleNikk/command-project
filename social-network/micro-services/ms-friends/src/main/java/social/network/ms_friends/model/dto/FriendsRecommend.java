package social.network.ms_friends.model.dto;

import java.util.List;

public class FriendsRecommend {

    private int countRecommend;

    private List<RelationshipDto> friends;

    public FriendsRecommend(List<RelationshipDto> friends, int countRecommend) {
        this.friends = friends;
        this.countRecommend = countRecommend;
    }

    public int getCountRecommend() {
        return countRecommend;
    }

    public void setCountRecommend(int countRecommend) {
        this.countRecommend = countRecommend;
    }

    public List<RelationshipDto> getFriends() {
        return friends;
    }

    public void setFriends(List<RelationshipDto> friends) {
        this.friends = friends;
    }
}