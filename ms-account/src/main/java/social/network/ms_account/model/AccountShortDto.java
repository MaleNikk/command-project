package social.network.ms_account.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountShortDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String photo;
    private String profileCover;
    private String about;
    private String city;
    private String country;
    private String statusCode;
    private LocalDateTime birthDate;
    private String messagePermission;
    private LocalDateTime lastOnlineTime;
    private String emojiStatus;
    private boolean deleted;
    private boolean blocked;
    private boolean isOnline;

    public AccountShortDto() {
    }

    public AccountShortDto(UUID id, String firstName, String lastName, String phone, String photo, String profileCover,
                           String about, String city, String country, String statusCode, LocalDateTime birthDate,
                           String messagePermission, LocalDateTime lastOnlineTime, String emojiStatus, boolean deleted,
                           boolean blocked, boolean isOnline) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.photo = photo;
        this.profileCover = profileCover;
        this.about = about;
        this.city = city;
        this.country = country;
        this.statusCode = statusCode;
        this.birthDate = birthDate;
        this.messagePermission = messagePermission;
        this.lastOnlineTime = lastOnlineTime;
        this.emojiStatus = emojiStatus;
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
