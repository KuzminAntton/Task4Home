package com.epam.task3.controller.commands.add_news;

import com.epam.task3.controller.commands.Command;
import com.epam.task3.util.Help;
import com.epam.task3.util.NewsValidator;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.ServiceFactory;
import com.epam.task3.service.exception.ServiceException;

public class AddNews implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();

        if(NewsValidator.newsValidateBySize(request)) {
            try{
                newsService.addNews(request);
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(Help.getWrongInput());
        }



        response = "News has been added successful.";

        return response;
    }
}
