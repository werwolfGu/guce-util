package com.guce.designPattern.commandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 * Created by Administrator on 2016/5/18.
 */
public class RequestPerson {
    private Command command;

    public void setCommand( Command command){
        this.command = command;
    }
    public void startExcuteCommand(){
        command.execute();
    }

    public static void main(String[] args) {
        PrintLetter printLetter = new PrintLetter();
        PrintNumber printNumber = new PrintNumber();

        Command c1 = new PrintEnglishCommand(printLetter);
        Command c2 = new PrintNumberCommand(printNumber);

        RequestPerson p = new RequestPerson();
        p.setCommand(c1);
        p.startExcuteCommand();
        System.out.println("\n------------------------------------------------");
        p.setCommand(c2);
        p.startExcuteCommand();

        System.out.println("\n------------------------------------------------");

        MacroCommand macroCommand = new MacroCommand();
        List<Command> list = new ArrayList<Command>();
        list.add(c1);
        list.add(c2);
        macroCommand.setList(list);
        p.setCommand(macroCommand);
        p.startExcuteCommand();
    }
}
