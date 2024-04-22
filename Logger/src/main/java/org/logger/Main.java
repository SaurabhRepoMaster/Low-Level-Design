package org.logger;

public class Main {
    public static void main(String[] args) {

        //Implemented using chain responsibility design pattern
        LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logObject.log(LogProcessor.INFO,"Just for Info!!");
        logObject.log(LogProcessor.DEBUG,"Need to debug this");
        logObject.log(LogProcessor.ERROR,"Error occured");

    }
}