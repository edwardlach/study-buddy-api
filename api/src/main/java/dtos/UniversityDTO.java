package dtos;

import models.University;

public class UniversityDTO extends AbstractDTO {

    private int universityId;
    private String name;
    private String location;

    public UniversityDTO(){}

    public UniversityDTO(University university) {
        this.universityId = university.getUniversityId();
        this.name = university.getName();
        this.location = university.getLocation();
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
