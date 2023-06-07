package com.example.proga_lab8.server.api;

import com.example.proga_lab8.my_programm.obj.Human;

import java.sql.DriverManager;
import java.sql.Timestamp;

public class GovernorApi extends BaseApi {
    public static synchronized Human get_governor(int governor_id) {
//        R_LOCK.lock();
        String query = "select * from governor where governor.id = " + governor_id + ";";
        Human governor = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
//            stmt.executeUpdate(query2);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Timestamp birthdate = rs.getTimestamp(3);
                governor = new Human(governor_id, birthdate, name);
            }

        } catch (Exception e) { // SQLException sqlEx
//            sqlEx.printStackTrace();
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (Exception e) { /*can't do anything */ }
            try {
                rs.close();
            } catch (Exception e) { /*can't do anything */ }
        }
//        R_LOCK.unlock();
        return governor;

    }

    public static synchronized int get_next_id() {
//        R_LOCK.lock();
        String query = "SELECT nextval('GoverSeq');";
        int id = 0;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) { // SQLException sqlEx
//            sqlEx.printStackTrace();
            e.printStackTrace();
        } finally {
            try { con.close(); } catch(Exception e) { /*can't do anything */ }
            try { stmt.close(); } catch(Exception e) { /*can't do anything */ }
            try { rs.close(); } catch(Exception e) { /*can't do anything */ }
        }
//        R_LOCK.unlock();
        return id;
    }

    public static synchronized String saveOrUpdate(Human gov) {
//        R_LOCK.lock();
        String result = "ok";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from governor where governor.id = " + gov.getId()+";");
            if (rs.next()) {
                stmt.executeUpdate("update governor set id = "+ gov.getId()+", name = '"+gov.getName()+"', birthday = '"+gov.getBirthday()+"' where governor.id = 1;");
            }
            stmt.executeUpdate("insert into governor (id, name, birthday) values ("+gov.getId()+", '"+gov.getName()+"', "+gov.getBirthday());
        } catch (Exception e) { // SQLException sqlEx
//            sqlEx.printStackTrace();
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            try { con.close(); } catch(Exception e) { /*can't do anything */ }
            try { stmt.close(); } catch(Exception e) { /*can't do anything */ }
            try { rs.close(); } catch(Exception e) { /*can't do anything */ }
        }
//        R_LOCK.unlock();
        return result;
    }
}
