package com.comm.log;

import com.comm.log.LogFormatter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Ĭ�Ͻ�log�ļ������C:\Logs\AIOClient\xxxx��\x��\xxxx-xx-xx.log
 * ·�������ڵĻ����Զ�����
 * ��ͨ���޸�getLogFilePath�޸����ɵ�log·��
 *
 * Created by pos on 2016/8/30.
 */
public class LogUtil {

    private static Calendar now = Calendar.getInstance();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final int year = now.get(Calendar.YEAR);

    private static final int month = now.get(Calendar.MONTH) + 1;

    private static final String LOG_FOLDER_NAME = LogUtil.class.getResource("/").getPath().replace("fastcreate/bin/", "");
    

    private static final String LOG_FILE_SUFFIX = ".log";

    private static Logger logger = Logger.getLogger("MyLogger");

    //ʹ��Ψһ��fileHandler����֤�����������־д��ͬһ���ļ���
    private static FileHandler fileHandler = getFileHandler();

    private static LogFormatter myLogFormatter = new LogFormatter();


    private synchronized static String getLogFilePath() {
        StringBuffer logFilePath = new StringBuffer();
//        logFilePath.append(System.getProperty("user.home"));
//        logFilePath.append("D:\\Logs");
//        logFilePath.append(File.separatorChar);
        logFilePath.append(LOG_FOLDER_NAME);
        logFilePath.append(File.separatorChar);
        logFilePath.append("logs");
        logFilePath.append(File.separatorChar);
        logFilePath.append(year+""+month);
//        logFilePath.append(File.separatorChar);
//        logFilePath.append(month);

        File dir = new File(logFilePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        logFilePath.append(File.separatorChar);
        logFilePath.append(sdf.format(new Date()));
        logFilePath.append(LOG_FILE_SUFFIX);

        return logFilePath.toString();
    }

    private static FileHandler getFileHandler() {
        FileHandler fileHandler = null;
        boolean APPEND_MODE = true;
        try {
            //�ļ���־���ݱ��Ϊ��׷��
            fileHandler = new FileHandler(getLogFilePath(), APPEND_MODE);
            return fileHandler;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized static Logger setLoggerHanlder() {
        return setLoggerHanlder(Level.ALL);
    }

    //    SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINESET
    public synchronized static Logger setLoggerHanlder(Level level) {

        try {
            //���ı�����ʽ���
//            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setFormatter(myLogFormatter);
            logger.addHandler(fileHandler);

            logger.setLevel(level);
        } catch (SecurityException e) {
            logger.severe(populateExceptionStackTrace(e));
        }
        return logger;
    }

    private synchronized static String populateExceptionStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append("\n");
        for (StackTraceElement elem : e.getStackTrace()) {
            sb.append("\tat ").append(elem).append("\n");
        }
        return sb.toString();
    }


}