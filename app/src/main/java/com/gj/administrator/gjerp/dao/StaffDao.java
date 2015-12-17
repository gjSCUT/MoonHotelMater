package com.gj.administrator.gjerp.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Hotel;

import com.gj.administrator.gjerp.domain.Staff;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STAFF".
*/
public class StaffDao extends AbstractDao<Staff, Long> {

    public static final String TABLENAME = "STAFF";

    /**
     * Properties of entity Staff.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Gender = new Property(2, String.class, "gender", false, "GENDER");
        public final static Property Rank = new Property(3, String.class, "rank", false, "RANK");
        public final static Property Positon = new Property(4, String.class, "positon", false, "POSITON");
        public final static Property Hotel_id = new Property(5, long.class, "hotel_id", false, "HOTEL_ID");
        public final static Property Dialog_id = new Property(6, Long.class, "dialog_id", false, "DIALOG_ID");
    };

    private DaoSession daoSession;


    public StaffDao(DaoConfig config) {
        super(config);
    }
    
    public StaffDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STAFF\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"GENDER\" TEXT NOT NULL ," + // 2: gender
                "\"RANK\" TEXT NOT NULL ," + // 3: rank
                "\"POSITON\" TEXT NOT NULL ," + // 4: positon
                "\"HOTEL_ID\" INTEGER NOT NULL ," + // 5: hotel_id
                "\"DIALOG_ID\" INTEGER);"); // 6: dialog_id
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_STAFF_NAME ON STAFF" +
                " (\"NAME\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STAFF\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Staff entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getGender());
        stmt.bindString(4, entity.getRank());
        stmt.bindString(5, entity.getPositon());
        stmt.bindLong(6, entity.getHotel_id());
 
        Long dialog_id = entity.getDialog_id();
        if (dialog_id != null) {
            stmt.bindLong(7, dialog_id);
        }
    }

    @Override
    protected void attachEntity(Staff entity) {
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
    public Staff readEntity(Cursor cursor, int offset) {
        Staff entity = new Staff( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // gender
            cursor.getString(offset + 3), // rank
            cursor.getString(offset + 4), // positon
            cursor.getLong(offset + 5), // hotel_id
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // dialog_id
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Staff entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setGender(cursor.getString(offset + 2));
        entity.setRank(cursor.getString(offset + 3));
        entity.setPositon(cursor.getString(offset + 4));
        entity.setHotel_id(cursor.getLong(offset + 5));
        entity.setDialog_id(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Staff entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Staff entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getHotelDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getDialogDao().getAllColumns());
            builder.append(" FROM STAFF T");
            builder.append(" LEFT JOIN HOTEL T0 ON T.\"HOTEL_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN DIALOG T1 ON T.\"DIALOG_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Staff loadCurrentDeep(Cursor cursor, boolean lock) {
        Staff entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Hotel hotel = loadCurrentOther(daoSession.getHotelDao(), cursor, offset);
         if(hotel != null) {
            entity.setHotel(hotel);
        }
        offset += daoSession.getHotelDao().getAllColumns().length;

        Dialog dialog = loadCurrentOther(daoSession.getDialogDao(), cursor, offset);
        entity.setDialog(dialog);

        return entity;    
    }

    public Staff loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Staff> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Staff> list = new ArrayList<Staff>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Staff> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Staff> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}