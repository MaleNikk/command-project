package social.network.ms_friends.service.application;

import org.slf4j.event.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import social.network.ms_friends.logging.ApplicationLogger;
import social.network.ms_friends.model.dto.RelationshipDto;
import social.network.ms_friends.model.dto.FriendsRecommend;
import social.network.ms_friends.model.dto.FriendshipStatus;
import social.network.ms_friends.model.entity.RelationshipEntity;
import social.network.ms_friends.model.mapping.DtoMapper;
import social.network.ms_friends.storage.ApplicationRepository;

import java.util.List;
import java.util.UUID;

@Component
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    private final ApplicationLogger logger;

    public ApplicationServiceImpl(ApplicationRepository repository, ApplicationLogger logger) {
        this.repository = repository;
        this.logger = logger;
    }


    @Override
    public List<RelationshipDto> findAll(String status, int page, int size, UUID id) {
        logger.printLog("Application service: call method find all friends by id.", Level.INFO);
        return repository.getFriends(id, FriendshipStatus.valueOf(status)).stream().map(DtoMapper::from).toList();
    }

    @Override
    public RelationshipDto getStatusWith(UUID id, UUID friendId) {
        logger.printLog("Application service: call method get status with friend", Level.INFO);
        return DtoMapper.from(repository.get(id,friendId));
    }

    @Override
    public RelationshipDto sendRequestTo(UUID id, UUID friendId) {
        logger.printLog("Application service: call method send request to friend.", Level.INFO);
        return DtoMapper.from(repository.save(DtoMapper.getInstance(id, friendId)));
    }

    @Override
    public RelationshipDto approveRequestFrom(UUID id, UUID friendId) {
        logger.printLog("Application service: call method approve request with friend.", Level.INFO);
        RelationshipEntity entity = repository.get(id,friendId);
        entity.setCurrentStatus(FriendshipStatus.FRIEND);
        entity.setTimeUpdate(System.currentTimeMillis());
        return DtoMapper.from(repository.edit(entity));
    }

    @Override
    public RelationshipDto block(UUID id, UUID friendId) {
        logger.printLog("Application service: call method block relationship with friend.", Level.INFO);
        RelationshipEntity entity = repository.get(id, friendId);
        if (entity.getBlockAuthor() == null) {
            entity.setCurrentStatus(FriendshipStatus.BLOCKED);
            entity.setTimeUpdate(System.currentTimeMillis());
            entity.setBlockAuthor(id);
            return DtoMapper.from(repository.edit(entity));
        } else {
            logger.printLog("Application service: relationship was blocked.", Level.INFO);
            return null;
        }
    }

    @Override
    public RelationshipDto unblock(UUID id, UUID friendId) {
        logger.printLog("Application service: call method unblock relationship with friend.", Level.INFO);
        RelationshipEntity entity = repository.get(id, friendId);
        if (entity.getBlockAuthor().equals(id)) {
            entity.setBlockAuthor(null);
            entity.setCurrentStatus(FriendshipStatus.FRIEND);
            entity.setTimeUpdate(System.currentTimeMillis());
            return DtoMapper.from(repository.edit(entity));
        } else {
            logger.printLog("Application service: only author can unblocked relationship.", Level.INFO);
            return null;
        }
    }

    @Override
    public RelationshipDto subscribe(UUID id, UUID friendId) {
        logger.printLog("Application service: call method subscribe relationship with friend.", Level.INFO);
        RelationshipEntity entity = repository.get(id, friendId);
        entity.setTimeUpdate(System.currentTimeMillis());
        entity.setCurrentStatus(FriendshipStatus.SUBSCRIBED);
        return DtoMapper.from(repository.edit(entity));
    }

    @Override
    public RelationshipDto deleteRelationWith(UUID id, UUID friendId) {
        logger.printLog("Application service: call method delete relationship.", Level.INFO);
        RelationshipEntity entity = repository.get(id, friendId);
        entity.setTimeUpdate(System.currentTimeMillis());
        entity.setCurrentStatus(FriendshipStatus.DELETE);
        entity.setBlockAuthor(id);
        return DtoMapper.from(repository.edit(entity));

    }

    @Override
    public FriendsRecommend recommendFriends(UUID id) {
        logger.printLog("Application service: call method find recommend friends.", Level.INFO);
        return null;
    }

    @Override
    public UUID checkToken(HttpHeaders headers) {
        logger.printLog("Application service: call method check token.", Level.INFO);
        return null;
    }
}