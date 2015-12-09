package com.gj.administrator.gjerp.util;

import com.gj.administrator.gjerp.vo.User;

import java.util.ArrayList;

/**
 * Created by guojun on 2015/12/07
 */
public class SessionUtil {
    private static final String[] hotelnames={"GuangZhou hotel","ShangHai hotel"};
    private static User user;
    private static String hotelname;
    private static String dbname;
    private static ArrayList<String> dblist;

    public static User getUser(){
        if(user ==null)
            user = new User();
        return user;
    }

    public static void setUser(User user) {
        SessionUtil.user = user;
    }

    public static String[] getHotelnames() {
        return hotelnames;
    }

    public static String getHotelname() {
        return hotelname;
    }

    public static void setHotelname(String hotelname) {
        SessionUtil.hotelname = hotelname;
    }

    public static String getDbname() {
        return dbname;
    }

    public static void setDbname(String dbname) {
        dbname = dbname;
    }

    public static ArrayList<String> getDblist() {
        return dblist;
    }

    public static void setDblist(ArrayList<String> dblist) {
        dblist = dblist;
    }
}
