package com.epam.task3.service;


import com.epam.task3.bean.News;
import com.epam.task3.dao.DAOFactory;
import com.epam.task3.dao.database.DBWorker;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.util.RequestWorker;

import java.util.HashSet;

public class NewsService implements ServiceNews {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DBWorker dbWorker = daoFactory.getDbWorker();

    /**
     * Take news from controller and give it to DAO layer.
     *
     * @param request string with news.
     * @throws ServiceException catch exception and throw it on service level.
     */
    @Override
    public void addNews(String request) throws ServiceException {
        try {
            dbWorker.addItem(request);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    /**
     * Return news from DAO layer to controller.
     *
     * @param request criterion for finding news.
     * @return list of news.
     * @throws ServiceException
     */

    @Override
    public HashSet<News> getNews(String request) throws ServiceException {
        try {
            request = RequestWorker.removePunct(request);

            request = request.toLowerCase();

            String[] parameterForTheSearch = request.split(" ");

            return dbWorker.searchNewsForFreeCriteria(parameterForTheSearch);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

}
