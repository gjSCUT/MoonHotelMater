package com.gj.administrator.gjerp.domain;

import com.gj.administrator.gjerp.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.gj.administrator.gjerp.dao.RoomTypeDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "ROOM_TYPE".
 */
public class RoomType {

    private Long id;
    private int type_name;
    private int bed_num;
    private double day_price;
    private Double hour_price;
    private Double longtime_price;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient RoomTypeDao myDao;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public RoomType() {
    }

    public RoomType(Long id) {
        this.id = id;
    }

    public RoomType(Long id, int type_name, int bed_num, double day_price, Double hour_price, Double longtime_price) {
        this.id = id;
        this.type_name = type_name;
        this.bed_num = bed_num;
        this.day_price = day_price;
        this.hour_price = hour_price;
        this.longtime_price = longtime_price;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRoomTypeDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType_name() {
        return type_name;
    }

    public void setType_name(int type_name) {
        this.type_name = type_name;
    }

    public int getBed_num() {
        return bed_num;
    }

    public void setBed_num(int bed_num) {
        this.bed_num = bed_num;
    }

    public double getDay_price() {
        return day_price;
    }

    public void setDay_price(double day_price) {
        this.day_price = day_price;
    }

    public Double getHour_price() {
        return hour_price;
    }

    public void setHour_price(Double hour_price) {
        this.hour_price = hour_price;
    }

    public Double getLongtime_price() {
        return longtime_price;
    }

    public void setLongtime_price(Double longtime_price) {
        this.longtime_price = longtime_price;
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
