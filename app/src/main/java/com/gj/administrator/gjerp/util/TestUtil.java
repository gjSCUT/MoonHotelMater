package com.gj.administrator.gjerp.util;

import android.content.Context;
import com.gj.administrator.gjerp.dao.CustomerDao;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.DialogDao;
import com.gj.administrator.gjerp.dao.HotelDao;
import com.gj.administrator.gjerp.dao.RegionDao;
import com.gj.administrator.gjerp.dao.RoomTypeDao;
import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.UserDao;
import com.gj.administrator.gjerp.domain.Customer;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.domain.Partner;
import com.gj.administrator.gjerp.domain.Region;
import com.gj.administrator.gjerp.domain.RoomType;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.Supplier;
import com.gj.administrator.gjerp.domain.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;
import jp.co.worksap.intern.constants.Messages;
import jp.co.worksap.intern.util.Utilities;

/**
 *
 * Created by Guojun on 2015/12/9.
 */

public class TestUtil {
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

    interface ReadLine{
        void handle(String[] arr) throws Exception;
    }

    public static  void readFromFile(InputStream inputStream, ReadLine readLine){
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(inputStream);
            if (isr != null && isr.ready()) {
                CSVReader csvReader = new CSVReader(isr);
                String[] value = null;
                try {
                    csvReader.readNext();
                    // String[] titleLine = csvReader.readNext();
                    while ((value = csvReader.readNext()) != null) {
                        if (value.length == 0){
                            continue;
                        }
                        String[] newFields = new String[value.length];
                        int i =0;
                        for (String field : value){
                            newFields[i++] = field.replace("\\n", "");
                        }
                        try {
                            readLine.handle(newFields);
                        } catch (Exception e) {
                            LogUtil.d(Messages.ERROR_READ_CSV, newFields[0]);
                        }
                    }
                } catch (IOException e) {
                    LogUtil.d(Messages.ERROR_READ_CSV, e.getMessage());
                } finally {
                    csvReader.close();
                }
            }
        } catch (FileNotFoundException e) {
            LogUtil.e(Messages.ERROR_CSV_NOT_FOUND, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }

    public static void createTestDate(Context context){
        //read csv file
        InputStream hotelStream = null,staffStream = null,customerStream = null,regionStream = null,roomTypeStream = null;
        try {
            hotelStream = context.getAssets().open("HOTEL_MST.csv");
            staffStream = context.getAssets().open("STAFF_MST.csv");
            customerStream = context.getAssets().open("CUSTOMER_MST.csv");
            regionStream = context.getAssets().open("REGION_MST.csv");
            roomTypeStream = context.getAssets().open("ROOM_MST.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DaoSession session = DBUtil.getDaoSession(context);
        //insert test case
        final HotelDao hotelDao = session.getHotelDao();
        final StaffDao staffDao = session.getStaffDao();
        final CustomerDao customerDao = session.getCustomerDao();
        final RegionDao regionDao = session.getRegionDao();
        final RoomTypeDao roomTypeDao = session.getRoomTypeDao();
        final UserDao userDao = session.getUserDao();

        final DialogDao dialogDao = session.getDialogDao();

        readFromFile(hotelStream, new ReadLine() {
            @Override
            public void handle(String[] arr) {
                //hotel_id,hotel_name,address,tel
                Hotel hotel = new Hotel(
                        Long.parseLong(arr[0]),arr[1],arr[2],arr[3]
                );
                hotelDao.insertOrReplace(hotel);
            }
        });
        readFromFile(staffStream, new ReadLine() {
            @Override
            public void handle(String[] arr) {
                //staff_id,name,gender,rank,position,hotel_id
                Staff staff = new Staff(
                        Long.parseLong(arr[0]),arr[1],arr[2],arr[3],arr[4],Long.parseLong(arr[5]),null
                );
                staffDao.insertOrReplace(staff);
            }
        });
        readFromFile(customerStream, new ReadLine() {
            @Override
            public void handle(String[] arr) {
                //customer_id,name,gender,birthday,nationality,passport_no,address,email,tel
                Customer customer = new Customer(
                        Long.parseLong(arr[0]),arr[1],arr[2], Utilities.parseDateStr(arr[3]),arr[4],arr[5],arr[6],arr[7],arr[8]
                );
                customerDao.insertOrReplace(customer);
            }
        });
        readFromFile(regionStream, new ReadLine() {
            @Override
            public void handle(String[] arr) {
                //region_id,name,manager_id
                Region region = new Region(
                        Long.parseLong(arr[0]),arr[1],Long.parseLong(arr[2])
                );
                regionDao.insertOrReplace(region);
            }
        });
        readFromFile(roomTypeStream, new ReadLine() {
            @Override
            public void handle(String[] arr) {
                //room_mst_id,room_type,price,price_unit
                RoomType roomType = new RoomType(
                        Long.parseLong(arr[0]), arr[1], Double.parseDouble(arr[2]), arr[3]
                );
                roomTypeDao.insertOrReplace(roomType);
            }
        });
        //User
        List<Staff> staffs = staffDao.loadAll();
        for(Staff staff:staffs){
            User user = new User(staff.getId(),staff.getName(),staff.getName(),staff.getId());
            userDao.insertOrReplace(user);
        }

        //Contacts
        String [] partnerType = {"Media","Airline","Travel","Restaurant ","OTA"};
        String [] supplierType = {"Food supply","Goods supply","Water supply ","Goods supply","Goods supply"};
        for(int i = 1;i<21;i++){
            Partner partner = new Partner(
                    (long)i,
                    "partner"+i,
                    partnerType[i%5],
                    "15918770336",
                    "gjscut@qq.com",
                    "WAP");
            Supplier supplier = new Supplier(
                    (long)i,
                    "supplier"+i,
                    supplierType[i%5],
                    "15918770336",
                    "gjscut@qq.com",
                    "WAP");
            session.getPartnerDao().insertOrReplace(partner);
            session.getSupplierDao().insertOrReplace(supplier);
        }

       /* Random random = new Random();
        for(int i=1;i<6;i++){
            Dialog dialog = new Dialog((long)i,"normal");
            session.getDialogDao().insertOrReplace(dialog);
        }
        //msg test
        for(int i=1;i<21;i++){
            com.gj.administrator.gjerp.domain.Message message = new com.gj.administrator.gjerp.domain.Message(
                    (long)i,
                    "Have finish the work",
                    name[i%5],
                    msg_type[i%5],
                    new Date(),
                    person_type[i%5],

                    user[i%5].getId(),
                    (long)i%5+1);
            session.getMessageDao().insertOrReplace(message);
        }*/
    }

}