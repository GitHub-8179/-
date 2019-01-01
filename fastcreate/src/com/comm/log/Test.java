package com.comm.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String [] args) {
        Logger logger = LogUtil.setLoggerHanlder();
        System.out.println(Test.class.getResource("/"));
//        Logger logger = LogUtil.setLoggerHanlder(Level.FINEST);
        logger.config("config");
        logger.fine("fine");
        logger.info("Hello, world!");
        logger.severe("What are you doing?");
        logger.warning("Warning !");
    }
}
