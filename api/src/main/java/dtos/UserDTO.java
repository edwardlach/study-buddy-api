package dtos;

import models.User;

import java.time.LocalDateTime;

public class UserDTO implements AbstractDTO<User, UserDTO> {

    private String created, updated;
    private boolean deleted;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int educationLevel, universityId;
    private int userId;

    public UserDTO() {}

    public UserDTO(User userEntity) {
        this.created = userEntity.getCreated().format(formatter);
        this.updated = userEntity.getUpdated().format(formatter);
        this.deleted = userEntity.isDeleted();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
        this.educationLevel = userEntity.getEducationLevel();
        this.universityId = userEntity.getUniversityId();
        this.userId = userEntity.getUserId();
    }

    @Override
    public UserDTO apply(User userEntity) {
        UserDTO uDTO = new UserDTO();
        uDTO.created = userEntity.getCreated().format(formatter);
        uDTO.updated = userEntity.getUpdated().format(formatter);
        uDTO.deleted = userEntity.isDeleted();
        uDTO.firstName = userEntity.getFirstName();
        uDTO.lastName = userEntity.getLastName();
        uDTO.email = userEntity.getEmail();
        uDTO.educationLevel = userEntity.getEducationLevel();
        uDTO.universityId = userEntity.getUniversityId();
        uDTO.userId = userEntity.getUserId();
        return uDTO;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
