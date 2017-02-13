package com.epam.task3.dao.database;

import com.epam.task3.bean.News;
import com.epam.task3.controller.Controller;
import com.epam.task3.dao.DAOFactory;
import com.epam.task3.dao.exception.ConnectionPoolException;
import com.mysql.jdbc.PreparedStatement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import static org.testng.Assert.*;


public class DBWorkerSearchMethodsTest {
    private ConnectionPool pool = new ConnectionPool();
    private Controller controller = Controller.getInstance();

    private HashSet<News> bookNews = new HashSet<>();
    private HashSet<News> filmNews = new HashSet<>();
    private HashSet<News> diskNews = new HashSet<>();

    private News news1 = new News("Mirrors", "Anton", "Film");
    private News news2 = new News("Atlant", "Mary", "Book");
    private News news3 = new News("Fifa17", "EASPORTS", "Disk");

    DAOFactory daoFactory = DAOFactory.getInstance();
    DBWorker dbWorker = daoFactory.getDbWorker();


    @BeforeClass
    public void setUp() {

        filmNews.add(news1);
        bookNews.add(news2);
        diskNews.add(news3);

        controller.init();

    }

    @AfterClass
    public void tearDown() throws SQLException {
        bookNews.clear();
        filmNews.clear();
        diskNews.clear();
        controller.destroy();

    }



    @Test
    public void testSearchNewsByTitle() throws Exception {
        Assert.assertEquals(filmNews, dbWorker.searchNewsByTitle("Mirrors"));
        Assert.assertEquals(bookNews, dbWorker.searchNewsByTitle("Atlant"));
        Assert.assertEquals(diskNews, dbWorker.searchNewsByTitle("Fifa17"));
    }

    @Test
    public void testSearchNewsByCreator() throws Exception {
        Assert.assertEquals(filmNews, dbWorker.searchNewsByCreator("Anton"));
        Assert.assertEquals(bookNews, dbWorker.searchNewsByCreator("Mary"));
        Assert.assertEquals(diskNews, dbWorker.searchNewsByCreator("EASPORTS"));
    }

    @Test
    public void testSearchNewsByCategory() throws Exception {
        Assert.assertEquals(filmNews, dbWorker.searchNewsByCategory("Film"));
        Assert.assertEquals(bookNews, dbWorker.searchNewsByCategory("Book"));
        Assert.assertEquals(diskNews, dbWorker.searchNewsByCategory("Disk"));
    }

    @Test
    public void testSearchNewsByTitleAndCategory() throws Exception {
        Assert.assertEquals(filmNews, dbWorker.searchNewsByTitleAndCategory("Mirrors", "Film"));
        Assert.assertEquals(bookNews, dbWorker.searchNewsByTitleAndCategory("Atlant", "Book"));
        Assert.assertEquals(diskNews, dbWorker.searchNewsByTitleAndCategory("Fifa17", "Disk"));
    }

    @Test
    public void testSearchNewsByCreatorAndCategory() throws Exception {
        Assert.assertEquals(filmNews, dbWorker.searchNewsByTitleAndCategory("Anton", "Film"));
        Assert.assertEquals(bookNews, dbWorker.searchNewsByTitleAndCategory("Mary", "Book"));
        Assert.assertEquals(diskNews, dbWorker.searchNewsByTitleAndCategory("EASPORTS", "Disk"));
    }

    @Test
    public void testSearchNewsByTitleAndCreator() throws Exception {
        Assert.assertEquals(filmNews, dbWorker.searchNewsByTitleAndCategory("Mirrors", "Anton"));
        Assert.assertEquals(bookNews, dbWorker.searchNewsByTitleAndCategory("Atlant", "Mary"));
        Assert.assertEquals(diskNews, dbWorker.searchNewsByTitleAndCategory("Fifa17", "EASPORTS"));
    }

    @Test
    public void testSearchConcreteNews() throws Exception {
        Assert.assertEquals(news1, dbWorker.searchConcreteNews(news1));
        Assert.assertEquals(news2, dbWorker.searchConcreteNews(news2));
        Assert.assertEquals(news3, dbWorker.searchConcreteNews(news3));

    }

}