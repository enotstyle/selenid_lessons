package api.homework;

public class LoginResponseModel {

//    {
//        "name": "morpheus",
//            "job": "leader",
//            "id": "122",
//            "createdAt": "2023-06-18T07:52:44.377Z"
//    }

    private String name, job, id, createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
