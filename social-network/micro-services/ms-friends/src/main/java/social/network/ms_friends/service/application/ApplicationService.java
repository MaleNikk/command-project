package social.network.ms_friends.service.application;

import org.springframework.http.HttpHeaders;
import social.network.ms_friends.model.dto.RelationshipDto;
import social.network.ms_friends.model.dto.FriendsRecommend;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

    List<RelationshipDto> findAll(String status, int page, int size, UUID id);

    RelationshipDto getStatusWith(UUID id, UUID friendId);

    RelationshipDto sendRequestTo(UUID id, UUID friendId);

    RelationshipDto approveRequestFrom(UUID id, UUID friendId);

    RelationshipDto block(UUID id, UUID friendId);

    RelationshipDto unblock(UUID id, UUID friendId);

    RelationshipDto subscribe(UUID id, UUID friendId);

    RelationshipDto deleteRelationWith(UUID id, UUID friendId);

    FriendsRecommend recommendFriends(UUID id);

    UUID checkToken(HttpHeaders headers);
}