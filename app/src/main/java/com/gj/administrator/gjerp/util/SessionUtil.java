package com.gj.administrator.gjerp.util;

import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.User;

import java.util.ArrayList;

/**
 * Created by guojun on 2015/12/07
 */
public class SessionUtil {
    private static final String[] hotelnames = {"GuangZhou hotel", "ShangHai hotel"};
    private static User user;
    private static Hotel hotel;

    public static String[] getHotelnames() {
        return hotelnames;
    }

    public static User getUser() {
        if (user == null)
            user = new User(null);
        return user;
    }

    public static void setUser(User user) {
        SessionUtil.user = user;
    }

    public static Hotel getHotel() {
        return hotel;
    }

    public static void setHotel(Hotel hotel) {
        SessionUtil.hotel = hotel;
    }
}
