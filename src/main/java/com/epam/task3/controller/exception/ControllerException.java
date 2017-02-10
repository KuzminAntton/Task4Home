package com.epam.task3.controller.exception;

/**
 * Created by Anton_Kuzmin on 2/2/2017.
 */
public class ControllerException extends Exception {
    private static final long serialVersionUID = 1L;

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Exception e) {
        super(e);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }
}
