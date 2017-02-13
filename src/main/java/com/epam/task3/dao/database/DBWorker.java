package com.epam.task3.dao.database;

import com.epam.task3.bean.News;
import com.epam.task3.dao.WorkWithNewsDao;
import com.epam.task3.dao.exception.ConnectionPoolException;
import com.epam.task3.dao.exception.DAOException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DBWorker implements WorkWithNewsDao {

    private ConnectionPool pool = new ConnectionPool();
    private String sqlSearchByFreeCriteriaInquiry = "SELECT * FROM news";
    private String sqlSearchByConcreteCriteriaInquiry = "SELECT * FROM news where ";
    private String sqlAddCommand = "INSERT INTO news(title,creator,category) VALUES(?,?,?)";

    private String titleRowName = "title";
    private String creatorRowName = "creator";
    private String categoryRowName = "category";

    public void init() throws DAOException {
        try {
            pool.initPollData();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    public void destroy() {
        pool.dispose();
    }

    @Override
    public HashSet<News> searchNewsForFreeCriteria(String[] request) throws DAOException {

        HashSet<News> news = new HashSet<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {

            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlSearchByFreeCriteriaInquiry);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String creator = rs.getString(creatorRowName);
                String title = rs.getString(titleRowName);
                String category = rs.getString(categoryRowName);
                for (String searchParameter : request) {
                    if (creator.toLowerCase().equals(searchParameter) ||
                            title.toLowerCase().equals(searchParameter) ||
                            category.toLowerCase().equals(searchParameter)) {
                        news.add(new News(title, creator, category));
                    }
                }

            }

        } catch (SQLException | ConnectionPoolException  e) {
            throw new DAOException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return news;
    }

    @Override
    public HashSet<News> searchNewsByTitle(String title) throws DAOException {
        HashSet<News> news ;

        news = addNewsFromConcreteColumnInHashSet(titleRowName,title);

        return news;
    }

    @Override
    public HashSet<News> searchNewsByCreator(String creator) throws DAOException {
        HashSet<News> news;

        news = addNewsFromConcreteColumnInHashSet(creatorRowName,creator);

        return news;
    }

    @Override
    public HashSet<News> searchNewsByCategory(String category) throws DAOException {
        HashSet<News> news;

        news = addNewsFromConcreteColumnInHashSet(categoryRowName,category);

        return news;
    }

    @Override
    public HashSet<News> searchNewsByTitleAndCategory(String title, String category) throws DAOException {
        HashSet<News> news = new HashSet<>();

        news.addAll(addNewsFromConcreteColumnInHashSet(titleRowName,title));
        news.addAll(addNewsFromConcreteColumnInHashSet(categoryRowName,category));

        return news;
    }

    @Override
    public HashSet<News> searchNewsByCreatorAndCategory(String creator, String category) throws DAOException {
        HashSet<News> news = new HashSet<>();

        news.addAll(addNewsFromConcreteColumnInHashSet(creatorRowName,creator));
        news.addAll(addNewsFromConcreteColumnInHashSet(categoryRowName,category));

        return news;
    }

    @Override
    public HashSet<News> searchNewsByTitleAndCreator(String title, String creator) throws DAOException {
        HashSet<News> news = new HashSet<>();

        news.addAll(addNewsFromConcreteColumnInHashSet(titleRowName,title));
        news.addAll(addNewsFromConcreteColumnInHashSet(creatorRowName,creator));

        return news;
    }

    @Override
    public News searchConcreteNews(News news) throws DAOException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {

            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlSearchByFreeCriteriaInquiry);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString(titleRowName);
                String creator = rs.getString(creatorRowName);
                String category = rs.getString(categoryRowName);
                if(news.equals(new News(title,creator,category))) {
                    return news;
                }

            }

        } catch (SQLException | ConnectionPoolException  e) {
            throw new DAOException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return null;
    }


    @Override
    public void addItem(String request) throws DAOException {
        Connection con = null;
        //ResultSet rs = null;
        PreparedStatement preparedStatement = null;


        String[] parameters = request.split(",");


        try {

            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlAddCommand);

            preparedStatement.setString(1, parameters[0]);
            preparedStatement.setString(2, parameters[1]);
            preparedStatement.setString(3, parameters[2]);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {

            throw new DAOException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private HashSet<News> addNewsFromConcreteColumnInHashSet(String columnName, String criteria) throws DAOException {

        HashSet<News> news = new HashSet<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {

            con = pool.takeConnection();

            preparedStatement = (PreparedStatement) con.prepareStatement(sqlSearchByConcreteCriteriaInquiry + columnName + " = " + "'" + criteria+ "'");

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String titleFromDB = rs.getString(titleRowName);
                String creatorFromDB = rs.getString(creatorRowName);
                String categoryFromDB = rs.getString(categoryRowName);
                news.add(new News(titleFromDB,creatorFromDB,categoryFromDB));
            }



        } catch (SQLException | ConnectionPoolException  e) {
            throw new DAOException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
     return news;
    }

//    private HashSet<News> addNewsFromConcreteColumnInHashSet(String firstColumnName, String secondColumnName, String sirstCriteria , String secondCriteria) throws DAOException {
//
//    }

}
