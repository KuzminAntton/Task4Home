package com.epam.task3.service;

import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.service.exception.ServiceException;

import java.io.IOException;
import java.util.HashSet;

public interface ServiceNews {
    public void addNews(String request) throws ServiceException;

    public HashSet<News> getNews(String request) throws ServiceException;

}
