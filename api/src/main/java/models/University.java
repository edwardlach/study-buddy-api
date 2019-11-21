package models;

public class University extends GenericEntity {
    private int universityId;
    private String name;

    public University(){}

    public University(int universityId, String name, String location) {
        this.universityId = universityId;
        this.name = name;
        this.location = location;
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

    private String location;

}
