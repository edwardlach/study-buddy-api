package models;

import dtos.UserDTO;

import java.time.LocalDateTime;

public class User {

    private LocalDateTime created, updated;
    private boolean deleted;
    private String firstName;
    private String lastName;
    private String email;
    private int educationLevel, universityId;
    private int userId;

    public User (LocalDateTime created, LocalDateTime updated, Boolean deleted, String firstName,
                 String lastName, String email, int educationLevel, int universityId) {
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.educationLevel = educationLevel;
        this.universityId = universityId;
    }

    public User (int userId, LocalDateTime created, LocalDateTime updated, Boolean deleted, String firstName,
                 String lastName, String email, int educationLevel, int universityId) {
        this.userId = userId;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.educationLevel = educationLevel;
        this.universityId = universityId;
    }

    public User() {}

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
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
