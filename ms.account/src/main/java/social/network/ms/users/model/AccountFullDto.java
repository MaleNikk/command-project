package social.network.ms.users.model;

import social.network.ms.users.model.dto.AccountRole;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountFullDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AccountRole role;          // USER или ADMIN
    private String phone;
    private String photo;
    private String profileCover;
    private String about;
    private String city;
    private String country;
    private String statusCode;
    private LocalDateTime regDate;
    private LocalDateTime birthDate;
    private String messagePermission;
    private LocalDateTime lastOnlineTime;
    private String emojiStatus;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private LocalDateTime deletionTimestamp;
    private boolean deleted;
    private boolean blocked;
    private boolean isOnline;

    public AccountFullDto() {
    }

    public AccountFullDto(UUID id, String firstName, String lastName, String email, String password, AccountRole role,
                          String phone, String photo, String profileCover, String about, String city,
                          String country, String statusCode, LocalDateTime regDate, LocalDateTime birthDate,
                          String messagePermission, LocalDateTime lastOnlineTime, String emojiStatus,
                          LocalDateTime createdOn, LocalDateTime updatedOn, LocalDateTime deletionTimestamp,
                          boolean deleted, boolean blocked, boolean isOnline) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.photo = photo;
        this.profileCover = profileCover;
        this.about = about;
        this.city = city;
        this.country = country;
        this.statusCode = statusCode;
        this.regDate = regDate;
        this.birthDate = birthDate;
        this.messagePermission = messagePermission;
        this.lastOnlineTime = lastOnlineTime;
        this.emojiStatus = emojiStatus;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.deletionTimestamp = deletionTimestamp;
        this.deleted = deleted;
        this.blocked = blocked;
        this.isOnline = isOnline;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProfileCover() {
        return profileCover;
    }

    public void setProfileCover(String profileCover) {
        this.profileCover = profileCover;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getMessagePermission() {
        return messagePermission;
    }

    public void setMessagePermission(String messagePermission) {
        this.messagePermission = messagePermission;
    }

    public LocalDateTime getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(LocalDateTime lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public String getEmojiStatus() {
        return emojiStatus;
    }

    public void setEmojiStatus(String emojiStatus) {
        this.emojiStatus = emojiStatus;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalDateTime getDeletionTimestamp() {
        return deletionTimestamp;
    }

    public void setDeletionTimestamp(LocalDateTime deletionTimestamp) {
        this.deletionTimestamp = deletionTimestamp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
