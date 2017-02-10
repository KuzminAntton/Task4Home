package com.epam.task3.controller.commands;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "Incorrect request";
    }
}
