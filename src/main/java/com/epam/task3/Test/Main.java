package com.epam.task3.Test;

import com.epam.task3.dao.database.ConnectionPool;
import com.epam.task3.dao.database.DBResourceManager;
import com.epam.task3.dao.exception.ConnectionPoolException;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Statement;
import com.sun.jndi.ldap.pool.PooledConnection;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    private static ConnectionPool pool = new ConnectionPool();
    private static String sqlSearchCommand = "SELECT * FROM news";
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
//
            pool.initPollData();
            con = pool.takeConnection();

            preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlSearchCommand);

            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                String creator = rs.getString("creator");
                String title = rs.getString("title");
                String category = rs.getString("category");
                System.out.println(title + " " + creator + " " + category);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public void sqlExample() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/news",
                    "root",
                    "aNTON1488"
            );
            String sql = "INSERT INTO news(title,creator,category) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"MyBook");
            ps.setString(2,"Anton");
            ps.setString(3,"Book");
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
