package com.example.framgia.hrm_10.model;

/**
 * Created by framgia on 08/06/2016.
 */
public class Staff {
    private int mId;
    private String mName;
    private String mPlaceOfBirth;
    private String mBirthday;
    private String mPhone;
    private int mIdPositionInCompany;
    private int mIdStatus;
    private int mLeftJob;

    public Staff(int id, String name, String placeOfBirth,
                 String birthday, String phone, int idPositionInCompany,
                 int idStatus, int leftJob) {
        this.mId = id;
        this.mName = name;
        this.mPlaceOfBirth = placeOfBirth;
        this.mBirthday = birthday;
        this.mPhone = phone;
        this.mIdPositionInCompany = idPositionInCompany;
        this.mIdStatus = idStatus;
        this.mLeftJob = leftJob;
    }

    public Staff(String name, String placeOfBirth,
                 String birthday, String phone, int idPositionInCompany,
                 int idStatus, int leftJob) {
        this.mName = name;
        this.mPlaceOfBirth = placeOfBirth;
        this.mBirthday = birthday;
        this.mPhone = phone;
        this.mIdPositionInCompany = idPositionInCompany;
        this.mIdStatus = idStatus;
        this.mLeftJob = leftJob;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPlaceOfBirth() {
        return mPlaceOfBirth;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public String getPhone() {
        return mPhone;
    }

    public int getIdPositionInCompany() {
        return mIdPositionInCompany;
    }

    public int getIdStatus() {
        return mIdStatus;
    }

    public int getLeftJob() {
        return mLeftJob;
    }
}
