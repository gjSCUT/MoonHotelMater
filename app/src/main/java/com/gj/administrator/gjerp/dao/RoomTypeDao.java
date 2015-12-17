package com.gj.administrator.gjerp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.gj.administrator.gjerp.domain.RoomType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ROOM_TYPE".
*/
public class RoomTypeDao extends AbstractDao<RoomType, Long> {

    public static final String TABLENAME = "ROOM_TYPE";

    /**
     * Properties of entity RoomType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type_name = new Property(1, String.class, "type_name", false, "TYPE_NAME");
        public final static Property Price = new Property(2, double.class, "price", false, "PRICE");
        public final static Property Price_unit = new Property(3, String.class, "price_unit", false, "PRICE_UNIT");
    };

    private DaoSession daoSession;


    public RoomTypeDao(DaoConfig config) {
        super(config);
    }
    
    public RoomTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ROOM_TYPE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TYPE_NAME\" TEXT NOT NULL ," + // 1: type_name
                "\"PRICE\" REAL NOT NULL ," + // 2: price
                "\"PRICE_UNIT\" TEXT NOT NULL );"); // 3: price_unit
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ROOM_TYPE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RoomType entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getType_name());
        stmt.bindDouble(3, entity.getPrice());
        stmt.bindString(4, entity.getPrice_unit());
    }

    @Override
    protected void attachEntity(RoomType entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RoomType readEntity(Cursor cursor, int offset) {
        RoomType entity = new RoomType( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // type_name
            cursor.getDouble(offset + 2), // price
            cursor.getString(offset + 3) // price_unit
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RoomType entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType_name(cursor.getString(offset + 1));
        entity.setPrice(cursor.getDouble(offset + 2));
        entity.setPrice_unit(cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(RoomType entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(RoomType entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
