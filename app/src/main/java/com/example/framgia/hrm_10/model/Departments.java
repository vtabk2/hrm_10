package com.example.framgia.hrm_10.model;

/**
 * Created by framgia on 07/06/2016.
 */
public class Departments {
    private int mId;
    private String mName;
    private int mIdImage;

    public Departments(int id, String name, int idImage) {
        this.mId = id;
        this.mName = name;
        this.mIdImage = idImage;
    }

    public Departments(String name, int idImage) {
        this.mName = name;
        this.mIdImage = idImage;
    }

    public int getIdImage() {
        return mIdImage;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
