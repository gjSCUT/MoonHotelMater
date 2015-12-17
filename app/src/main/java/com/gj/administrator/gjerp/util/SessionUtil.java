package com.gj.administrator.gjerp.util;

import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Staff;

/**
 * Created by guojun on 2015/12/14
 */
public class SessionUtil {
    private static Staff staff;
    private static Hotel hotel;

    public static Staff getStaff() {
        return staff;
    }

    public static void setStaff(Staff user) {
        SessionUtil.staff = user;
    }

    public static Hotel getHotel() {
        return hotel;
    }

    public static void setHotel(Hotel hotel) {
        SessionUtil.hotel = hotel;
    }
}
