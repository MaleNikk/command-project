package social.network.ms.users.model.entity;

import java.time.LocalDateTime;

public class AccountUpdateDto {

    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String phone;
    private String about;
    private String city;
    private String country;
    private String emojiStatus;
    private String photo;
    private String profileCover;

    public AccountUpdateDto() {
    }

    public AccountUpdateDto(String firstName, String lastName, LocalDateTime birthDate, String phone,
                            String about, String city, String country, String emojiStatus, String photo, String profileCover) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.about = about;
        this.city = city;
        this.country = country;
        this.emojiStatus = emojiStatus;
        this.photo = photo;
        this.profileCover = profileCover;
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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmojiStatus() {
        return emojiStatus;
    }

    public void setEmojiStatus(String emojiStatus) {
        this.emojiStatus = emojiStatus;
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
}
