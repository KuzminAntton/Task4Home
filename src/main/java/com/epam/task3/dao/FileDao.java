package com.epam.task3.dao;


import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;

import java.io.IOException;
import java.util.HashSet;

public interface FileDao {
    HashSet<News> searchNewsInFIle(String request) throws  DAOException;
    void addItem(String request) throws DAOException;
}
