package com.gj.administrator.gjerp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.dao.RoomTypeDao;
import com.gj.administrator.gjerp.dao.RoomDao;
import com.gj.administrator.gjerp.dao.UserDao;
import com.gj.administrator.gjerp.dao.EmployeeDao;
import com.gj.administrator.gjerp.dao.GuestDao;
import com.gj.administrator.gjerp.dao.CommentDao;
import com.gj.administrator.gjerp.dao.BookRecordDao;
import com.gj.administrator.gjerp.dao.OutRecordDao;
import com.gj.administrator.gjerp.dao.TravelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1000): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1000;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        HotelDao.createTable(db, ifNotExists);
        RoomTypeDao.createTable(db, ifNotExists);
        RoomDao.createTable(db, ifNotExists);
        UserDao.createTable(db, ifNotExists);
        EmployeeDao.createTable(db, ifNotExists);
        GuestDao.createTable(db, ifNotExists);
        CommentDao.createTable(db, ifNotExists);
        BookRecordDao.createTable(db, ifNotExists);
        OutRecordDao.createTable(db, ifNotExists);
        TravelDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        HotelDao.dropTable(db, ifExists);
        RoomTypeDao.dropTable(db, ifExists);
        RoomDao.dropTable(db, ifExists);
        UserDao.dropTable(db, ifExists);
        EmployeeDao.dropTable(db, ifExists);
        GuestDao.dropTable(db, ifExists);
        CommentDao.dropTable(db, ifExists);
        BookRecordDao.dropTable(db, ifExists);
        OutRecordDao.dropTable(db, ifExists);
        TravelDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(HotelDao.class);
        registerDaoClass(RoomTypeDao.class);
        registerDaoClass(RoomDao.class);
        registerDaoClass(UserDao.class);
        registerDaoClass(EmployeeDao.class);
        registerDaoClass(GuestDao.class);
        registerDaoClass(CommentDao.class);
        registerDaoClass(BookRecordDao.class);
        registerDaoClass(OutRecordDao.class);
        registerDaoClass(TravelDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
