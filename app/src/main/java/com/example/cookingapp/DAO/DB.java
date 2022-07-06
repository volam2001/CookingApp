package com.example.cookingapp.DAO;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public  static Connection getConnection(){
        Connection connection =null;
        String Url="jdbc:mysql://localhost:3306/cookingeasy";
        String username="admin";
        String password="123456789";
        String cnstr = "jdbc:mysql://localhost:3306/cookingeasy?user=admin&password=123456789&useUnicode=true&characterEncoding=UTF-8";
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.d("CHECKED","Sucess fefwf");
            connection = DriverManager.getConnection(cnstr);
            Log.d("CHECKED","Sucess");
        } catch (SQLException throwables) {
            Log.d("CHECKED",throwables.getMessage()+" ");
            Log.d("loi sql",throwables.getMessage());
        } catch (ClassNotFoundException e) {
            Log.d("CHECKED",e.getMessage()+" ");
            Log.d("loi ClassNotFoundException",e.getMessage());

        }


        return  connection;

    }
}
