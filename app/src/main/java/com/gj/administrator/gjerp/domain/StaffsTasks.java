package com.gj.administrator.gjerp.domain;

import com.gj.administrator.gjerp.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.StaffsTasksDao;
import com.gj.administrator.gjerp.dao.TaskDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "STAFFS_TASKS".
 */
public class StaffsTasks {

    private Long id;
    private long staff_id;
    private long task_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient StaffsTasksDao myDao;

    private Staff staff;
    private Long staff__resolvedKey;

    private Task task;
    private Long task__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public StaffsTasks() {
    }

    public StaffsTasks(Long id) {
        this.id = id;
    }

    public StaffsTasks(Long id, long staff_id, long task_id) {
        this.id = id;
        this.staff_id = staff_id;
        this.task_id = task_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStaffsTasksDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    /** To-one relationship, resolved on first access. */
    public Staff getStaff() {
        long __key = this.staff_id;
        if (staff__resolvedKey == null || !staff__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StaffDao targetDao = daoSession.getStaffDao();
            Staff staffNew = targetDao.load(__key);
            synchronized (this) {
                staff = staffNew;
            	staff__resolvedKey = __key;
            }
        }
        return staff;
    }

    public void setStaff(Staff staff) {
        if (staff == null) {
            throw new DaoException("To-one property 'staff_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.staff = staff;
            staff_id = staff.getId();
            staff__resolvedKey = staff_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Task getTask() {
        long __key = this.task_id;
        if (task__resolvedKey == null || !task__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskDao targetDao = daoSession.getTaskDao();
            Task taskNew = targetDao.load(__key);
            synchronized (this) {
                task = taskNew;
            	task__resolvedKey = __key;
            }
        }
        return task;
    }

    public void setTask(Task task) {
        if (task == null) {
            throw new DaoException("To-one property 'task_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.task = task;
            task_id = task.getId();
            task__resolvedKey = task_id;
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
