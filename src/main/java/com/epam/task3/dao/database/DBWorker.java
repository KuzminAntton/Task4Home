package com.epam.task3.dao.database;

import com.epam.task3.bean.News;
import com.epam.task3.dao.WorkWithNewsDataDao;
import com.epam.task3.dao.exception.ConnectionPoolException;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.service.util.RequestWorker;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DBWorker implements WorkWithNewsDataDao {

    private ConnectionPool pool = new ConnectionPool();
    private String sqlSearchCommand = "SELECT * FROM news";
    private String sqlAddCommand = "INSERT INTO news(title,creator,category) VALUES(?,?,?)";

    @Override
    public HashSet<News> searchNewsForFreeCriteria(String[] request) throws DAOException {

        HashSet<News> news = new HashSet<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {

            pool.initPollData();
            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlSearchCommand);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String creator = rs.getString("creator");
                String title = rs.getString("title");
                String category = rs.getString("category");
                for (String searchParameter : request) {
                    if (creator.toLowerCase().equals(searchParameter) ||
                            title.toLowerCase().equals(searchParameter) ||
                            category.toLowerCase().equals(searchParameter)) {
                        news.add(new News(category, title, creator));
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return news;
    }

    @Override
    public void addItem(String request) throws DAOException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;


        String[] parameters = request.split(",");


        try {
            pool.initPollData();
            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlAddCommand);

            preparedStatement.setString(1, parameters[0]);
            preparedStatement.setString(2, parameters[1]);
            preparedStatement.setString(3, parameters[2]);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {

            throw new DAOException(e);
        }

    }
}
