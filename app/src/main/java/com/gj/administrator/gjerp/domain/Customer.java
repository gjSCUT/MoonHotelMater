package com.gj.administrator.gjerp.domain;

import com.gj.administrator.gjerp.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.gj.administrator.gjerp.dao.CustomerDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "CUSTOMER".
 */
public class Customer {

    private Long id;
    /** Not-null value. */
    private String name;
    /** Not-null value. */
    private String gender;
    /** Not-null value. */
    private java.util.Date birthday;
    /** Not-null value. */
    private String nationality;
    /** Not-null value. */
    private String passport_no;
    /** Not-null value. */
    private String address;
    /** Not-null value. */
    private String email;
    /** Not-null value. */
    private String telphone;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CustomerDao myDao;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(Long id, String name, String gender, java.util.Date birthday, String nationality, String passport_no, String address, String email, String telphone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.nationality = nationality;
        this.passport_no = passport_no;
        this.address = address;
        this.email = email;
        this.telphone = telphone;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCustomerDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    /** Not-null value. */
    public String getGender() {
        return gender;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /** Not-null value. */
    public java.util.Date getBirthday() {
        return birthday;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    /** Not-null value. */
    public String getNationality() {
        return nationality;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /** Not-null value. */
    public String getPassport_no() {
        return passport_no;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    /** Not-null value. */
    public String getAddress() {
        return address;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Not-null value. */
    public String getEmail() {
        return email;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Not-null value. */
    public String getTelphone() {
        return telphone;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}