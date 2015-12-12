package com.gj.administrator.gjerp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.gj.administrator.gjerp.base.BaseApplication;

import java.util.Random;

/**
 *
 * Created by Guojun on 2015/12/9.
 */

public class RandomUtil {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}