package com.gj.administrator.gjerp.tests;

import android.test.AndroidTestCase;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.gj.administrator.gjerp.domain.Guest;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Room;

import java.lang.reflect.Field;

/**
 * test for guest
 * Created by Guo Jun on 2015/12/10.
 */
public class HotelTest extends AndroidTestCase {
    public void testTableInfo() throws  Exception{
        assertNotNull(Cache.getContext());
        TableInfo tableInfo = Cache.getTableInfo(Guest.class);
        assertNotNull(tableInfo);
        assertEquals("guests", tableInfo.getTableName());

        for ( Field field : tableInfo.getFields() ) {
            // Id column is a special case, we'll ignore that one.
            if ( field.getName().equals("mId") ) continue;
            assertEquals(field.getName(), tableInfo.getColumnName(field));
        }

    }

    public void testSava() throws Exception{
        Hotel hotel1 = new Hotel("ShangHai hotel");
        hotel1.save();
        Hotel hotel2= new Hotel("GuangZhou hotel");
        hotel2.save();
        Guest query = Model.load(Guest.class, hotel1.getId());
        query = Model.load(Guest.class, hotel2.getId());
    }

    public void testUpdate() throws Exception{

    }

    public void testRemove() throws Exception{

    }
}
