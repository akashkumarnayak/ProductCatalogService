package org.example.productcatelogservice.models;

import java.util.Date;

public abstract class BaseModel {

    public long id;
    Date createdAt;
    Date updatedAt;
    State state;
}
