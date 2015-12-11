package com.gj.administrator.gjerp.tests;

import android.test.AndroidTestCase;

import com.activeandroid.Cache;
import com.activeandroid.TableInfo;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.dao.GuestDao;
import com.gj.administrator.gjerp.domain.Guest;

import java.lang.reflect.Field;
import java.util.Date;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * test for guest
 * Created by Guo Jun on 2015/12/10.
 */
public class GuestTest extends AndroidTestCase {
    Guest guest;
    long id;
    GuestDao dao = BaseApplication.getDaoSession(getContext()).getGuestDao();

    public void testInsert() throws Exception{
        guest = new Guest(null, "Guo jun",1, "man","ID card",21,new Date(),null,"360728199410300098","15918770336",null,null,null,null);
        id = dao.insert(guest);
        guest = dao.load(id);
        assertNotNull(guest);
        assertEquals(guest.getName(),"Guo jun");
    }

    public void testQuery() throws Exception{
        QueryBuilder<Guest> qb = dao.queryBuilder();
        qb.where(GuestDao.Properties.Id.eq(id));
        qb.buildCount().count();
        assertTrue(qb.buildCount().count() > 0);
    }

    public void testUpdate() throws Exception{
        guest = new Guest(id, "Xiao Hong",1, "man","ID card",21,new Date(),null,"360728199410300098","15918770336",null,null,null,null);
        id = dao.insertOrReplace(guest);
        guest = dao.load(id);
        assertNotNull(guest);
        assertEquals(guest.getName(),"Xiao Hong");
    }

    public void testRemove() throws Exception{
        Long id = guest.getId();
        dao.delete(guest);
        QueryBuilder<Guest> qb = dao.queryBuilder();
        qb.where(GuestDao.Properties.Id.eq(id));
        qb.buildCount().count();
        assertTrue(qb.buildCount().count() == 0);
    }
}
