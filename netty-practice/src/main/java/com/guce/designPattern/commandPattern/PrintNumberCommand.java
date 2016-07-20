package com.guce.designPattern.commandPattern;

/**
 * Created by Administrator on 2016/5/18.
 */
public class PrintNumberCommand implements Command {

    private PrintNumber printNumber;
    public PrintNumberCommand(PrintNumber printLetter){
        this.printNumber = printLetter;
    }
    public void execute() {
        printNumber.printNumber();
    }
}
