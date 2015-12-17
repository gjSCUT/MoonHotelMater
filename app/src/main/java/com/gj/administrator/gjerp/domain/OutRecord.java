package com.gj.administrator.gjerp.domain;

import com.gj.administrator.gjerp.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.gj.administrator.gjerp.dao.CustomerDao;
import com.gj.administrator.gjerp.dao.OutRecordDao;
import com.gj.administrator.gjerp.dao.RoomDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "OUT_RECORD".
 */
public class OutRecord {

    private Long id;
    /** Not-null value. */
    private java.util.Date arrive_date;
    /** Not-null value. */
    private java.util.Date leave_date;
    private double consumption;
    private long guest_id;
    private long room_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient OutRecordDao myDao;

    private Customer customer;
    private Long customer__resolvedKey;

    private Room room;
    private Long room__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public OutRecord() {
    }

    public OutRecord(Long id) {
        this.id = id;
    }

    public OutRecord(Long id, java.util.Date arrive_date, java.util.Date leave_date, double consumption, long guest_id, long room_id) {
        this.id = id;
        this.arrive_date = arrive_date;
        this.leave_date = leave_date;
        this.consumption = consumption;
        this.guest_id = guest_id;
        this.room_id = room_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOutRecordDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public java.util.Date getArrive_date() {
        return arrive_date;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setArrive_date(java.util.Date arrive_date) {
        this.arrive_date = arrive_date;
    }

    /** Not-null value. */
    public java.util.Date getLeave_date() {
        return leave_date;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLeave_date(java.util.Date leave_date) {
        this.leave_date = leave_date;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public long getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(long guest_id) {
        this.guest_id = guest_id;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    /** To-one relationship, resolved on first access. */
    public Customer getCustomer() {
        long __key = this.guest_id;
        if (customer__resolvedKey == null || !customer__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomerDao targetDao = daoSession.getCustomerDao();
            Customer customerNew = targetDao.load(__key);
            synchronized (this) {
                customer = customerNew;
            	customer__resolvedKey = __key;
            }
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new DaoException("To-one property 'guest_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.customer = customer;
            guest_id = customer.getId();
            customer__resolvedKey = guest_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Room getRoom() {
        long __key = this.room_id;
        if (room__resolvedKey == null || !room__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RoomDao targetDao = daoSession.getRoomDao();
            Room roomNew = targetDao.load(__key);
            synchronized (this) {
                room = roomNew;
            	room__resolvedKey = __key;
            }
        }
        return room;
    }

    public void setRoom(Room room) {
        if (room == null) {
            throw new DaoException("To-one property 'room_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.room = room;
            room_id = room.getId();
            room__resolvedKey = room_id;
        }
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
