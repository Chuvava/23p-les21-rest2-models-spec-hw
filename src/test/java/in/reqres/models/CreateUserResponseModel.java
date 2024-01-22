package in.reqres.models;

import lombok.Data;

import java.util.Date;

@Data
public class CreateUserResponseModel {

    String name, job;
    Integer id;
    Date createdAt;
}
