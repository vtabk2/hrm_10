package com.example.framgia.hrm_10.model;
/**
 * Created by framgia on 07/06/2016.
 */
public class Departments {
    private int mId;
    private String mName;
    private int mIdImage;
    public Departments() {
        super();
    }
    public Departments(String name, int idImage) {
        this.mName = name;
        this.mIdImage = idImage;
    }
    public void setIdImage(int mIdImage) {
        this.mIdImage = mIdImage;
    }
    public int getIdImage() {
        return mIdImage;
    }
    public int getId() {
        return mId;
    }
    public void setId(int mId) {
        this.mId = mId;
    }
    public String getName() {
        return mName;
    }
    public void setName(String mName) {
        this.mName = mName;
    }
}
