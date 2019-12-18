package models;

import java.time.LocalDateTime;

public class Subject extends GenericEntity {

    private int classId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean deleted;
    private String name;
    private int universityId;
    private int classNumber;
    private String subject;
    private University university;

    public Subject(){}

    public Subject(
            int classId,
            LocalDateTime created,
            LocalDateTime updated,
            boolean deleted, String name,
            int universityId,
            int classNumber,
            String subject) {
        this.classId = classId;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.name = name;
        this.universityId = universityId;
        this.classNumber = classNumber;
        this.subject = subject;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
