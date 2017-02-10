package com.epam.task3.controller.util;

import com.epam.task3.controller.commands.CommandName;

public final class Help {
    private static String wrongInput = "Please use only supported commands : \n" +
            CommandName.ADD_NEWS + "\n" +
            CommandName.GET_NEWS + "\n" +
            "Add news request must look like : ADD_NEWS-Category,Name,Creator" + "\n"
            +"Search news request must look like : GET_NEWS-tag1...tagN";

    public static String getWrongInput() {
        return wrongInput;
    }
}
