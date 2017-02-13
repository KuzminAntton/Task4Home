package com.epam.task3.view;


import com.epam.task3.controller.Controller;
import com.epam.task3.controller.exception.ControllerException;
import com.epam.task3.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommunication {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Controller controller = Controller.getInstance();


    public static void main(String[] args) throws IOException, ControllerException, ServiceException {

        ConsoleCommunication consoleCommunication = new ConsoleCommunication();

        consoleCommunication.consoleCommunication();

    }

    public void consoleCommunication() throws IOException, ControllerException {
        String request = "";

            controller.init();

            while(!request.toLowerCase().equals("exit-")) {

                request = reader.readLine();

                Controller controller = Controller.getInstance();
                controller.executeTask(request);

            }


        controller.destroy();
    }
}
//get_news_by_creator-anton
//get_news_by_category-book