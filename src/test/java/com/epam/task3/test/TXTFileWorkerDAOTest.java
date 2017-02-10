package com.epam.task3.test;

import com.epam.task3.bean.News;
import com.epam.task3.dao.DAOFactory;
import com.epam.task3.dao.txt_file.TXTWorkWithNewsDataWorkerDAO;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;


public class TXTFileWorkerDAOTest {
    private TXTWorkWithNewsDataWorkerDAO txtFileWorkerDAO;
    private String request ;
    private String pathToDataFileTestSearch;
    private String pathToDataFileTestAdd;
    HashSet<News> news;
    @BeforeClass
    public void setUp() throws Exception {
        news = new HashSet<>();
        request = "Category,Title,Creator";
        pathToDataFileTestAdd = "DataSourceForTestAdd.txt";
        pathToDataFileTestSearch = "DataSourceForTestSearch.txt";
        news.add(new News("Category","Title","Creator"));
        txtFileWorkerDAO = DAOFactory.getInstance().getTxtFileWorkerDAO();
        FileWriter writer = new FileWriter(pathToDataFileTestSearch, true);
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        bufferWriter.write("\n" + request);
        bufferWriter.close();
    }

    @AfterClass
    public void tearDown() throws Exception {
        try {
            FileWriter fstream1 = new FileWriter(pathToDataFileTestSearch);
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
            FileWriter fstream2 = new FileWriter(pathToDataFileTestAdd);
            BufferedWriter out2 = new BufferedWriter(fstream2);
            out2.write("");
            out2.close();
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());}
    }

    @DataProvider (name = "dataForPositiveSearchTests")
    public Object[][] getcorrectSearchData() {
        return new Object[][] {
                {"Title"},
                {"Creator"},
                {"Category"},
                {"TITLE"},
                {"CATEGORY"},
                {"CREATOR"},
                {"title"},
                {"category"},
                {"creator"},
                {"title,creator,category"},
                {"title:creator:category"},
                {"title,:;\"'creato:categor"},
                {"title$creato:categor"},
                {"title@@@@@@@@creato:categor"},

        };
    }

    @DataProvider (name = "dataForNegativeTests")
    public Object[][] getincorrectData() {
        return new Object[][] {
                {null},
                {""},
                {"WWW"},
                {",:!@"},
                {"CATEGOR"},
                {"CREATO"},
                {"tITl"},
        };
    }


    @Test (dataProvider = "dataForPositiveSearchTests")
    public void testPositiveSearchNewsInFIle(String data) throws Exception {
        txtFileWorkerDAO.setPathToNewsDataFile(pathToDataFileTestSearch);
        Assert.assertEquals(news,txtFileWorkerDAO.searchNewsForFreeCriteria(data));
    }

    @Test (dataProvider = "dataForNegativeTests")
    public void testNegativeSearchNewsInFIle(String data) throws Exception {
        txtFileWorkerDAO.setPathToNewsDataFile(pathToDataFileTestSearch);
        Assert.assertTrue(txtFileWorkerDAO.searchNewsForFreeCriteria(data).isEmpty());
    }

    @DataProvider (name = "dataForPositiveAddTests")
    public Object[][] getincorrectADDData() {
        return new Object[][] {
                {"Category,Title,Creator"},
                {"category,title,creator"},
                {"CATEGORY,TITLE,CREATOR"},
                {" CATEGORY , TITLE , CREATOR "},
        };
    }

    @Test (dataProvider = "dataForPositiveAddTests")
    public void testPositiveAddItem(String data) throws Exception {
        txtFileWorkerDAO.setPathToNewsDataFile(pathToDataFileTestAdd);
        txtFileWorkerDAO.addItem(data);
        boolean res = false;
        List<String> lines = Files.readAllLines(Paths.get(pathToDataFileTestAdd));
        for (String line : lines) {
            if(line.equals(data)) {
                res = true;
            }
        }
        Assert.assertTrue(res);
    }

}