package com.example.framgia.hrm_10.model.data;

/**
 * Created by framgia on 13/06/2016.
 */
public class Status {
    private int mId;
    private String mTypeStatus;

    public Status(int id, String typeStatus) {
        this.mId = id;
        this.mTypeStatus = typeStatus;
    }

    public Status(String typeStatus) {
        this.mTypeStatus = typeStatus;
    }

    public int getId() {
        return mId;
    }

    public String getTypeStatus() {
        return mTypeStatus;
    }
}
