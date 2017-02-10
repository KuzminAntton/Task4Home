package com.epam.task3.dao;


import com.epam.task3.dao.database.DBWorker;
import com.epam.task3.dao.txt_file.TXTWorkWithNewsDataWorkerDAO;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final TXTWorkWithNewsDataWorkerDAO txtFileWorkerDAO = new TXTWorkWithNewsDataWorkerDAO();

    private final DBWorker dbWorker = new DBWorker();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public TXTWorkWithNewsDataWorkerDAO getTxtFileWorkerDAO() {
        return txtFileWorkerDAO;
    }

    public DBWorker getDbWorker() {
        return dbWorker;
    }

}
