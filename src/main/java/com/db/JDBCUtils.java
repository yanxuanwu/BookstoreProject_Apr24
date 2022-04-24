package com.db;

import com.exception.DBException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    private static DataSource dataSource=null;
    static {
        dataSource = new ComboPooledDataSource("javawebapp");

    }

    public static Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("connection error");
        }

    }

    public static void release(ResultSet rs, Statement statement){
        try {
            if (rs!=null){
                rs.close();
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("connection error");
        }
        try {
            if (statement!=null){
                statement.close();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("connection error");
        }
    }

    public static void release(Connection connection){
        try {
            if (connection!=null){
                connection.close();
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("connection error");
        }

    }
}
