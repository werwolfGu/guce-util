package com.guce.designPattern.commandPattern;

/**
 * Created by Administrator on 2016/5/18.
 */
public class PrintEnglishCommand implements Command {
    private PrintLetter printLetter;
    public     PrintEnglishCommand(PrintLetter printLetter){
        this.printLetter = printLetter;
    }

    public void execute() {
        printLetter.printEnglish();
    }
}
