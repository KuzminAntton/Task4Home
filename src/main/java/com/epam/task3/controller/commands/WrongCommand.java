package com.epam.task3.controller.commands;

import com.epam.task3.controller.commands.Command;

public class WrongCommand implements Command {

    @Override
    public String execute(String request) {
        return "Command is incorrect";
    }
}
