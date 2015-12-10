package com.gj.administrator.gjerp.tests;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import com.activeandroid.Configuration;
import com.activeandroid.Model;
import com.gj.administrator.gjerp.domain.Guest;

import java.io.IOException;

/**
 * test for configuration
 * Created by Guo Jun on 2015/12/10.
 */
public class ConfigrationTest extends AndroidTestCase {
    public void testDefaultValue() throws IOException, ClassNotFoundException {
        Configuration conf = new Configuration.Builder(getContext()).create();
        assertNotNull(conf.getContext());
        assertEquals(1024, conf.getCacheSize());
        assertEquals("Application.db", conf.getDatabaseName());
        assertEquals(1, conf.getDatabaseVersion());
        assertNull(conf.getModelClasses());
        assertFalse(conf.isValid());
        assertNull(conf.getTypeSerializers());
        assertEquals(Configuration.SQL_PARSER_LEGACY, conf.getSqlParser());
    }

}
