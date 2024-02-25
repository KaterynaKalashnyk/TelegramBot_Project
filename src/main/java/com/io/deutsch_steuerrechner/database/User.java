package com.io.deutsch_steuerrechner.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity(name = "usersData")
public class User {
    @Id
    private Long cahtId;

    private String UserName;
    private Timestamp timeOfFirstStart;

    public void setCahtId(Long cahtId) {
        this.cahtId = cahtId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setTimeOfFirstStart(Timestamp timeOfFirstStart) {
        this.timeOfFirstStart = timeOfFirstStart;
    }

    public Long getCahtId() {
        return cahtId;
    }

    public String getUserName() {
        return UserName;
    }

    public Timestamp getTimeOfFirstStart() {
        return timeOfFirstStart;
    }
}
