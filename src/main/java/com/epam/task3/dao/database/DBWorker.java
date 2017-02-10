package com.epam.task3.dao.database;

import com.epam.task3.bean.News;
import com.epam.task3.dao.FileDao;
import com.epam.task3.dao.exception.ConnectionPoolException;
import com.epam.task3.dao.exception.DAOException;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DBWorker implements FileDao {
    private ConnectionPool pool = new ConnectionPool();
    private String sqlSearchCommand = "SELECT * FROM news";
    @Override
    public HashSet<News> searchNewsInFIle(String request) throws DAOException {
       HashSet<News> news = new HashSet<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {

            pool.initPollData();
            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlSearchCommand);

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

        return news;
    }

    @Override
    public void addItem(String request) throws DAOException {

    }
}
