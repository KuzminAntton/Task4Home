package com.epam.task3.controller.commands;

import com.epam.task3.service.exception.ServiceException;

import java.io.IOException;

public interface Command {
    public String execute(String request) throws IOException, ServiceException;
}
