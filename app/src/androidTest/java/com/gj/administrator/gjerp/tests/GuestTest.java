package com.gj.administrator.gjerp.tests;

import android.test.AndroidTestCase;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.query.Select;
import com.gj.administrator.gjerp.domain.Guest;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * test for guest
 * Created by Guo Jun on 2015/12/10.
 */
public class GuestTest extends AndroidTestCase {
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
        Guest guest1 = new Guest("Guo Jun", Guest.TYPE.NORMAL,"identity","360728199410300098","15918770336","MAN",null,null, null, null);
        guest1.save();
        Guest guest2 = new Guest("He He", Guest.TYPE.NORMAL,"identity","360728199410300098","15918770336","MAN",null,null,null,null);
        guest2.save();
        Guest query = Model.load(Guest.class, guest1.getId());
        query = Model.load(Guest.class, guest2.getId());
    }

    public void testUpdate() throws Exception{

    }

    public void testRemove() throws Exception{

    }
}
