package social.network.ms_friends.web;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import social.network.ms_friends.logging.ApplicationLogger;
import social.network.ms_friends.model.dto.RelationshipDto;
import social.network.ms_friends.model.dto.FriendsCount;
import social.network.ms_friends.model.dto.FriendsRecommend;
import social.network.ms_friends.service.application.ApplicationService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/friends")
@RestController
public class ApplicationController {

    private final ApplicationService service;

    private final ApplicationLogger logger;

    @Autowired
    public ApplicationController(ApplicationService service, ApplicationLogger logger) {
        this.service = service;
        this.logger = logger;
    }

    @GetMapping({"/count"})
    public ResponseEntity<FriendsCount> getCountRequests(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestHeader HttpHeaders headers) {
//        /api/v1/friends/count:
//    get:
//      tags:
//        - friends-controller
//      summary: Получение количества запросов дружбы с пользователем
//      operationId: getFriendRequestCount
//      responses:
//        '200':
//          description: OK
//          content:
//            '*/*':
//              schema:
//                type: integer
//                format: int32
        logger.printLog("Get count friends with current account.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            List<RelationshipDto> friends = service.findAll(status, page, size, currentId);
            return ResponseEntity.ok(new FriendsCount(friends.size()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RelationshipDto>> getCountFriends(
            @RequestParam(required = false, defaultValue = "FRIEND") String status,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method get all relationships with current account.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        //Response - List<UUID> users;
        return currentId != null ? ResponseEntity.ok(service.findAll(status, page, size, currentId)) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RelationshipDto>> getCountByStatus(
            @RequestParam(required = false, defaultValue = "FRIEND") String status,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method get all relationships by status.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        //Response - List<UUID> users;
        return currentId != null ? ResponseEntity.ok(service.findAll(status, page, size, currentId)) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



    @GetMapping({"/{id}"})
    public ResponseEntity<RelationshipDto> getStatus(
            @PathVariable UUID id,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: get relationship by id.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        return currentId != null ? ResponseEntity.ok(service.getStatusWith(id, currentId)) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping({"/{id}/request"})
    public ResponseEntity<RelationshipDto> sendRequest(
            @PathVariable UUID id,
            @RequestHeader HttpHeaders headers,
            UriComponentsBuilder uriBuilder) {
        logger.printLog("Call to method: send request to friend.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.sendRequestTo(currentId, id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping({"/{id}/approve"})
    public ResponseEntity<RelationshipDto> approveRequest(
            @PathVariable UUID id,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: approve request.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            service.approveRequestFrom(id, currentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping({"/block/{id}"})
    public ResponseEntity<RelationshipDto> block(
            @PathVariable UUID id,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: block relationship.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            service.block(id, currentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping({"/unblock/{id}"})
    public ResponseEntity<RelationshipDto> unblock(
            @PathVariable UUID userId,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: unblock.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            service.unblock(userId, currentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping({"/subscribe/{id}"})
    public ResponseEntity<RelationshipDto> subscribe(
            @PathVariable UUID userId,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: subscribe.",Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            service.subscribe(userId, currentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteRelation(
            @PathVariable UUID userId,
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: delete relation.", Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            service.deleteRelationWith(userId, currentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping({"/recommendations"})
    public ResponseEntity<FriendsRecommend> recommend(
            @RequestHeader HttpHeaders headers) {
        logger.printLog("Call to method: recommend.",Level.INFO);
        UUID currentId = service.checkToken(headers);
        if (currentId != null) {
            return ResponseEntity.ok(service.recommendFriends(currentId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/friendId")
    public ResponseEntity<List<?>> getAllRelationship() {
//        /api/v1/friends/friendId:
//    get:
//      tags:
//        - friends-controller
//      summary: Получение списка id всех отношений с пользователем
//      operationId: getAllFriendsIdList
//      responses:
//        '200':
//          description: OK
//          content:
//            '*/*':
//              schema:
//                type: array
//                items:
//                  type: string
//                  format: uuid
        return null;
    }

    @GetMapping("/friendId/{id}")
    public ResponseEntity<List<?>> getAllRelationshipById() {
//       /api/v1/friends/friendId/{id}:
//    get:
//      tags:
//        - friends-controller
//      summary: Получение списка id всех отношений с пользователем по id
//      operationId: getFriendsIdListByUserId
//      parameters:
//        - name: id
//          in: path
//          required: true
//          schema:
//            type: string
//            format: uuid
//      responses:
//        '200':
//          description: OK
//          content:
//            '*/*':
//              schema:
//                type: array
//                items:
//                  type: string
//                  format: uuid
        return null;
    }

    @GetMapping("/check")
    public ResponseEntity<List<?>> checkRelationship(@RequestParam List<?> data, @RequestHeader HttpHeaders headers) {
//        /api/v1/friends/check:
//    get:
//      tags:
//        - friends-controller
//      summary: Получение текущего статуса отшонений двух пользователей
//      operationId: getStatuses
//      parameters:
//        - name: ids
//          in: query
//          required: true
//          schema:
//            type: array
//            items:
//              type: string
//              format: uuid
//      responses:
//        '200':
//          description: OK
//          content:
//            '*/*':
//              schema:
//                type: string
        return null;
    }

    @GetMapping("/blockFriendId")
    public ResponseEntity<List<?>> getListFriendsWhoBlocked() {
//      /api/v1/friends/blockFriendId:
//    get:
//      tags:
//        - friends-controller
//      summary: Получение списка id пользователей, которые заблокировали пользователя
//      operationId: getFriendsWhoBlockedUser
//      responses:
//        '200':
//          description: OK
//          content:
//            '*/*':
//              schema:
//                type: array
//                items:
//                  type: string
//                  format: uuid
        return null;
    }




}