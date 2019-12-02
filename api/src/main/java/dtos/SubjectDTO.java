package dtos;

import models.Subject;
import models.University;

public class SubjectDTO extends AbstractDTO {

    private int classId;
    private String created;
    private String updated;
    private boolean deleted;
    private String name;
    private int universityId;
    private int classNumber;
    private String subject;
    private UniversityDTO university;

    public SubjectDTO(){}

    public SubjectDTO(Subject subjectEntity) {
        this.classId = subjectEntity.getClassId();
        this.created = subjectEntity.getCreated().format(formatter);
        this.updated = subjectEntity.getUpdated().format(formatter);
        this.deleted = subjectEntity.isDeleted();
        this.name = subjectEntity.getName();
        this.universityId = subjectEntity.getUniversityId();
        this.classNumber = subjectEntity.getClassNumber();
        this.subject = subjectEntity.getSubject();
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public UniversityDTO getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDTO university) {
        this.university = university;
    }

}
