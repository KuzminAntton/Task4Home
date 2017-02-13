package com.epam.task3.controller.commands.search_news;

import com.epam.task3.bean.News;
import com.epam.task3.controller.commands.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.ServiceFactory;
import com.epam.task3.service.exception.ServiceException;

import java.io.IOException;

public class GetNewsByCreator implements Command{
    @Override
    public String execute(String request) throws IOException, ServiceException {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();

        try{
            for(News news : newsService.getNewsByCreator(request)){
                System.out.println(news);
            }

        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }


        response = "News that I could find";
        return response;
    }
}
