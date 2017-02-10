package com.epam.task3.controller.commands;

import com.epam.task3.controller.util.Help;

import java.util.HashMap;
import java.util.Map;


public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {

        repository.put(CommandName.ADD_NEWS, new AddNews());
        repository.put(CommandName.GET_NEWS, new GetNewsForFreeCriteria());
        repository.put(CommandName.GET_NEWS_FREE_CRIT, new GetNewsForFreeCriteria());
        repository.put(CommandName.WRONG_COMMAND, new WrongCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());

    }

    public Command getCommand(String name) {

        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);

        } catch (IllegalArgumentException | NullPointerException e) {

            System.out.println(Help.getWrongInput());
            command = repository.get(CommandName.WRONG_REQUEST);

        }
        return command;
    }
}
