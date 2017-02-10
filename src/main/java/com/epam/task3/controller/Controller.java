package com.epam.task3.controller;

import com.epam.task3.controller.commands.Command;
import com.epam.task3.controller.commands.CommandProvider;
import com.epam.task3.controller.exception.ControllerException;
import com.epam.task3.controller.util.Help;
import com.epam.task3.service.exception.ServiceException;

import java.io.IOException;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    private static final Controller instance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }


    private final String paramDelimeter = "-";

    public String executeTask(String request) throws ControllerException {
        try {

            String commandName;
            Command executionCommand;
            commandName = request.substring(0, request.indexOf(paramDelimeter));
            executionCommand = provider.getCommand(commandName);
            String response;
            request = request.replace(commandName, "");
            request = request.replace(paramDelimeter, "");
            response = executionCommand.execute(request);

            return response;
        }catch (ServiceException e1) {
            System.out.println(e1.getMessage());
        }
        catch(IOException | StringIndexOutOfBoundsException e2) {
            System.out.println(Help.getWrongInput());
        }

        return "";
    }

}
