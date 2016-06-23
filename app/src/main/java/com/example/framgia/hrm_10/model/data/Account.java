package com.example.framgia.hrm_10.model.data;

/**
 * Created by framgia on 22/06/2016.
 */
public class Account {
    private int mId;
    private String mName;
    private String mPass;
    private int mPermission;

    public Account(int id, String name, String pass, int permission) {
        this.mId = id;
        this.mName = name;
        this.mPass = pass;
        this.mPermission = permission;
    }

    public Account(String name, String pass, int permission) {
        this.mName = name;
        this.mPass = pass;
        this.mPermission = permission;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPass() {
        return mPass;
    }

    public int getPermission() {
        return mPermission;
    }
}
